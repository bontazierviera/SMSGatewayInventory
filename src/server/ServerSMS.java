package server;

//import javax.comm.*;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServerSMS {
    private SMSInterface in=null;
    
    public ServerSMS(SMSInterface in) {
        this.in = in;
    }
    
    private static int panjangKarakter = 0;
    private static StringBuffer stringBuffer = null;
    private static String balikKarakter(String karakter) {
  	panjangKarakter = karakter.length();
    	stringBuffer = new StringBuffer(panjangKarakter);
    	for (int i = 0; (i + 1) < panjangKarakter; i = i + 2) {
      		stringBuffer.append(karakter.charAt(i + 1));
      		stringBuffer.append(karakter.charAt(i));
    	}
    	return new String(stringBuffer);
    }
    
    private static char[] hexa;
    private static char[] karakter;
    private static String rubahKeHexa(int a) {
    	char[] hexa = {
        	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        	'E', 'F'};

    	karakter = new char[2];
    	// Mengambil hanya 8 bit 255d = 11111111 b
    	a = a & 255;
    	// hasil pembagian dengan 16
    	karakter[0] = hexa[a / 16];
    	// sisa hasil pembagian dengan 16
    	karakter[1] = hexa[a % 16];

    	return new String(karakter);
    }
  	
    private static char[] gsmToAsciiMap; // GSM ==> ASCII
    private static String delapanKeTujuhBit(String pesan, int msglen) {
    	int i, o, r = 0, rlen = 0, olen = 0, charcnt = 0;
    	StringBuffer msg = new StringBuffer(160);
    	int pesanlen = pesan.length();
    	String ostr;
    	char c;

    	// pengulangan hingga nilai terpenuhi
    	// i + 1 < pesanlen dan charcnt < msglen
    	for (i = 0; ( (i + 1) < pesanlen) && (charcnt < msglen); i = i + 2) {
      		// mengambil dua digit Hexadesimal
      		ostr = pesan.substring(i, i + 2);
      		o = Integer.parseInt(ostr, 16);
      		// berikan nilai olen = 8
      		olen = 8;

      		// geser posisi semua bit ke kiri sebanyak rlen bit
      		o <<= rlen;
      		o |= r; // berikan sisa bit dari o ke r
      		olen += rlen; // olen = olen + rlen

      		c = (char) (o & 127); // mendapatkan nilai o menjadi 7 bit
      		o >>>= 7; // geser posis bit ke kanan sebanyak 7 bit
      		olen -= 7;

      		r = o; // menaruh sisa bit dari o ke r.
      		rlen = olen;

      		c = gsmToAsciiMap[c]; // rubah ke Text (kode ASCII)
      		msg.append(c); // tambahkan ke msg
      		charcnt++; // nilai charcnt ditambahkan 1

      		// jika rlen >= 7
      		if (rlen >= 7) {
        		c = (char) (r & 127);
        		r >>>= 7;
        		rlen -= 7;
        		msg.append(c);
        		charcnt++;
      		}
    	} // Akhir for
    	if ( (rlen > 0) && (charcnt < msglen)) {
      		msg.append( (char) r);
    	}
    	return msg.toString();
    }
  
    private static char[] asciiToGsmMap; // ASCII ==> GSM
    private static String tujuhKeDelapanBit(String pesan) {

    	StringBuffer msg = new StringBuffer(pesan);

    	StringBuffer encmsg = new StringBuffer(2 * 160);
    	int bb = 0, bblen = 0, i;
    	char o = 0, c = 0, tc;

    	for (i = 0; i < msg.length() || bblen >= 8; i++) {
      		if (i < msg.length()) {
        		c = msg.charAt(i);
        		tc = asciiToGsmMap[c];

        		c = tc;

        		c &= ~ (1 << 7);
        		bb |= (c << bblen);
        		bblen += 7;
      		}

      		while (bblen >= 8) {
        		o = (char) (bb & 255);
        		encmsg.append(rubahKeHexa(o));
        		bb >>>= 8;
        		bblen -= 8;
      		}
    	}
    	if ( (bblen > 0)) {
      		encmsg.append(rubahKeHexa(bb));
    	}
    	return encmsg.toString();
    }
  	
    static {
    	final int lastindex = 255;
    	gsmToAsciiMap = new char[lastindex + 1];
    	asciiToGsmMap = new char[lastindex + 1];
    	int i;

    	for (i = 0; i <= lastindex; i++) {
      		gsmToAsciiMap[i] = asciiToGsmMap[i] = (char) i;
    	}
    }
  	
    private String infoSmsc = null;
    private int nilaiSmsc = 0;
    private int nomorSmsc = 0;
    private String panjangNotlp = null;
    private int nilaiPanjangNotlp = 0;
    private int nilaiNotlp = 0;
    private String Notlp = null;
    private String dapatNotlp = null;
    private String panjangPesan = null;
    private int nilaiPanjangPesan = 0;
    private String pesanPDU = null;
    private String pesan = null;
    private void PduTerimaSms(String smspdu) {
    	int i = 0;
    	try {
      		// Mengambil nilai panjang informasi SMSC
      		infoSmsc = smspdu.substring(i, 2);
      		nilaiSmsc = Integer.parseInt(infoSmsc, 16);
      		// format nomor dan nomor MSC dibuang
      		i = i + 4;
      		nomorSmsc = i + (nilaiSmsc * 2) - 2;
      		// Nilai PDU Type dibuang
      		i = nomorSmsc + 2;
      		// Mengambil Panjang Nomor Telepon Pengirim
      		panjangNotlp = smspdu.substring(i, i + 2);
      		nilaiPanjangNotlp = Integer.parseInt(panjangNotlp, 16);
      		// format nomor pengirim dibuang
      		i = i + 4;
      		nilaiNotlp = i + nilaiPanjangNotlp + nilaiPanjangNotlp % 2;
      		// Nomor telepon pengirim
      		Notlp = smspdu.substring(i, nilaiNotlp);
      		dapatNotlp = balikKarakter(Notlp);
      		i = nilaiNotlp;
      		// Nilai PID, DCS, dan SCTS dibuang
      		i = i + 18;
      		// Mengambil Panjang Pesan SMS
      		panjangPesan = smspdu.substring(i, i + 2);
      		nilaiPanjangPesan = Integer.parseInt(panjangPesan, 16);
      		i = i + 2;
      		pesanPDU = smspdu.substring(i, smspdu.length());
      		pesan = delapanKeTujuhBit(pesanPDU, nilaiPanjangPesan);
    	}
    	catch (Exception e) {}
    }
  	
    private static StringBuffer pesanPDUKirim = null;
    private static int panjangNotlpTujuan = 0;
    private static int panjangPesanKirim = 0;
    private static String PduPesan = null;
    private static String PduKirimSms(String notlp, String pesan) {
    	pesanPDUKirim = new StringBuffer(320); // 320 = 160 * 2 (panjang max)
    	// Tambahkan nilai PDU Type ---> Default = 11
    	pesanPDUKirim.append("11");
    	// Tambahkan nilai MR ---> Default = 00
    	pesanPDUKirim.append("00");
    	// Tambahkan nilai panjang nomor pengirim
    	panjangNotlpTujuan = notlp.length();
    	pesanPDUKirim.append(rubahKeHexa(panjangNotlpTujuan));
    	// Tambah nilai format no. telepon --> format internasional = 91
    	pesanPDUKirim.append("91");
    	// Tambahkan nilai nomor telepon pengirim
    	// Jika panjang notlp adalah ganjil
    	if ( (notlp.length() % 2) == 1) {
      		notlp = balikKarakter(notlp + "F");
    	}
    	// Jika panjang notlp adalah genap
    	else {
      		notlp = balikKarakter(notlp);
    	}
    	pesanPDUKirim.append(notlp);
    	// tambahkan nilai PID ---> Default = 00
    	pesanPDUKirim.append("00");
    	// tambahkan nilai DCS ---> Default = 00
    	pesanPDUKirim.append("00");
    	// tambahkan nilai VP = 4 hari ---> AA h
    	pesanPDUKirim.append("AA");
    	panjangPesanKirim = pesan.length();
    	PduPesan = tujuhKeDelapanBit(pesan);
    	pesanPDUKirim.append(rubahKeHexa(panjangPesanKirim));
    	pesanPDUKirim.append(PduPesan);

    	return new String(pesanPDUKirim);

    }
    int bufferOffset = 0;
    byte[] bacaBuffer = new byte[100000];
    int n;
  	
    private String[] hasil;
    private int Index;
    private int panjangPDU;
    private int PDU = 0;
    private String respons;
    private StringTokenizer st;
    private void terimaAT(String buffer) {
    	// Menguraikan buffer berdasarkan karakter CRLF
    	st = new StringTokenizer(buffer, "\r\n");

    	while (st.hasMoreTokens()) {
      		// mengambil token yang ada pada obyek
      		respons = st.nextToken();
      		
      		if(in != null) {
      			in.responTerminal(respons);
      		}
      		
      		// Memproses respon yang diterima
      		try 
      		{
        		// Jika Ada Telepon yang Masuk
        		if (respons.startsWith("RING")) 
        		{
        	  		kirimAT("ATH0" + "\15", 100); // Diputuskan
        		} // Akhir if "RING"

     		  	// Jika ada Pesan Baru yang Masuk
        		else if (respons.startsWith("+CMTI:")) 
        		{                            
                            Pattern pattern = Pattern.compile(",");
                            hasil = pattern.split(respons.trim());//split:mrubah jd array brdasarkan (",")
                            Index = Integer.parseInt(hasil[1].trim());
          		    kirimAT("AT+CMGR=" + Index + "\15", 1250); // Baca Pesan Baru yang Masuk
        		} // Akhir if "+CMTI:"

        		// Jika ada Pesan Baru Yang dibaca
        		else if (respons.startsWith("+CMGR:")) {
          			PDU = 1;
        		} // Akhir if "+CMGR:"

        		// Membaca Pesan Indox yang belum dibaca
        		else if (respons.startsWith("+CMGL")) {
          			Pattern pattern = Pattern.compile(":");
          			hasil = pattern.split(respons.trim());
          			pattern = Pattern.compile(",");
          			hasil = pattern.split(hasil[1].trim());
          			Index = Integer.parseInt(hasil[0].trim());
          			PDU = 1;

        		} // Akhir if "+CMGL"
        		else if (PDU == 1) {
          			prosesTerimaSms(Index, respons.trim());
          			PDU = 0;
        		}
                        else if(respons.startsWith("+CNMI")) {
                            Pattern pattern = Pattern.compile(":");
                            hasil = pattern.split(respons.trim());
                            String cnmi=hasil[1].trim();
                            ParsingCNMI parsing=new ParsingCNMI(cnmi);
                            parsing.Parsing();
                            //kirimAT("AT+CNMI=" + parsing.getCNMI() + "\15", 1250);
                            //kirimAT("AT+CNMI=\"0,3,3,1,0\"" +"\15", 1250);
                            kirimAT("AT+CNMI=1,1,0,2,0" +"\15", 1250);
                        }
        		else {}
      		}
      		catch (Exception e) {} // Akhir while
    	}
    }
  
    private void prosesTerimaSms(int Index, String Pdu) {
    	try {
      		// Rubah dari format PDU menjadi Format Teks
      		PduTerimaSms(Pdu);
    	} // Akhir try
    	catch (Exception e) {}
    	// Jika nomor telepon pengirim diakhiri dengan "F"
    	if (dapatNotlp.endsWith("F")) {
      		// Buang karakter "F"
      		dapatNotlp = dapatNotlp.substring(0, dapatNotlp.length() - 1);
    	}
    	// Hapus Pesan yang Telah dibaca
    	kirimAT("AT+CMGD=" + Index + "\15", 1250);
        Thread t = new Thread(){
            public void run(){            
                if(in != null) {
                    in.pesanSMSMasuk(dapatNotlp,pesan);
                }            
            }
        };
        t.start();
    }
  	
    SerialPort port = null;
    Enumeration portList = null;
    CommPortIdentifier portId = null;
    InputStream input;
    OutputStream output;
    String portName="COM1"; // Nama Port
    int nilaiBaud = 19200; // Nilai Baud Rate
    int nilaiData = SerialPort.DATABITS_8; // Nilai DATABITS
    int nilaiStop = SerialPort.STOPBITS_1; // Nilai STOPBITS
    int nilaiParity = SerialPort.PARITY_NONE; // Nilai PARITY
    int nilaiFlow = SerialPort.FLOWCONTROL_NONE; // Nilai FLOWCONTROL
    
    public void setTerminal() {
    	// Mencari daftar port-port yang tersedia
    	Enumeration portList = CommPortIdentifier.getPortIdentifiers();
    	while (portList.hasMoreElements()) {
      		// Mengambil nilai-nilai port yang ditemukan
      		CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
      		// Hanya Port Serial yang diambil
      		if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
        		// Buka port berdasarkan nama port yang telah ditentukan
                    if (portId.getName().equals(portName)) {
                    try {
            			port = (SerialPort) portId.open("SMS_GATEWAY_PT.Palito Nusantara", 5000);                                
                                if(in != null) {
                                      in.responTerminal("Server Sedang Mencoba Membuka Port "+portName+"...");
                                      System.out.println("Server Sedang Mencoba Membuka Port "+portName+"...");
                                }
                        break;
                    } //Akhir try open port
                    catch (PortInUseException piue) {
                         if(in != null) {
                             in.responTerminal("Port Sedang Digunakan Oleh Sistem Lain!!!");
                             System.out.println("PORT SEDANG DIGUNAKAN OLEH SISTEM LAIN ");
                         }
                        System.out.println("PORT SEDANG DIGUNAKAN OLEH SISTEM LAIN ");
                    } //Akhir catch
        		} //Akhir if port getName
      		} // Akhir if port getPortType
    	} // Akhir while
    	
    	// Membuka input dan output Stream pada Port
    	try {
      		output = port.getOutputStream();
      		input = port.getInputStream();
    	} // Akhir try stream
    	catch (IOException ioe) {           
            JOptionPane.showMessageDialog(null,"Gagal Membuka Stream pada metode setTerminal class: ServerSMS\n" +
             "Kesalahan Pada : "+ioe.getMessage(),"ERROR INFORMATION",JOptionPane.ERROR_MESSAGE);
            if(in != null) {
                  in.responTerminal("Gagal Membuka Stream...");
                  in.responTerminal("Terjadi kesalahan pada : "+ioe);
                  in.responTerminal("Server Sedang Mencoba Membuka Port "+portName+"...");
                  System.out.println("Gagal Membuka Stream... Terjadi kesalahan pada : "+ioe);
            }
    	} //Akhir catch
              
        
    	// Mengatur Konfigurasi dari Serial Port
    	try {
      		port.setSerialPortParams(nilaiBaud, nilaiData, nilaiStop, nilaiParity);
      		port.setFlowControlMode(nilaiFlow);
      		// Menerima pemberitahuan jika ada data pada terminal
      		port.notifyOnDataAvailable(true);
                
                  // Cetak pesan ke layar
                if(in != null) {
                      in.responTerminal("Server Melakukan Hubungan ke Port : " + portName);
                      in.responTerminal("Server Berhasil Terhubung ke Port : " + portName);
                      in.responTerminal("Server Sedang melakukan Pengaturan Terminal");
                      in.responTerminal("Tunggu Sebentar...");
                      System.out.println("Server Berhasil Terhubung ke Port : " + portName);
                }
      		// Melakukan pengatur TERMINAL
      		kirimAT("AT" + "\15", 1250); // Apakah terminal telah siap
            kirimAT("AT+CSQ" + "\15", 1250);  // mengetahui kualitas sinyal
      		kirimAT("AT+CGMI" + "\15", 1250); // mengetahui nama manufacture
      		kirimAT("AT+GMM" + "\15", 1250);  // mengetahui model hp
      		kirimAT("AT+CGSN" + "\15", 1250); // mengetahui imei / serial number hp
      		kirimAT("AT+CMGF=0" + "\15", 1250); // Menetapkan Format PDU Mode
      		kirimAT("AT+CSCS=\"GSM\"" + "\15", 1250); // Menetapkan Encoding
      		kirimAT("AT+CNMI=1,1,0,2,0" + "\15", 1250); // mendeteksi pesan masuk secara otomatis
            kirimAT("AT+CPMS=\"SM\"" + "\15", 1250); // Membaca pesan dari memori hp
      		kirimAT("AT+CMGL=0" + "\15", 1250); // Membaca pesan yang belum dibaca yang ada di dalam Inbox
               
                String jamBuka = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss ").format(new java.util.Date());  
                if(in != null) {
                      in.responTerminal("PORT "+portName+ " Berhasil Dibuka...");
                      in.responTerminal("Koneksi Berhasil Dilakukan Pada : "+jamBuka);
                }
                
    	} //Akhir try serial port params
    	catch (UnsupportedCommOperationException ucoe) {
                if(in != null) {
                      in.responTerminal("Pengaturan Data serial Port Gagal...");
                      in.responTerminal("PORT "+portName+" Gagal Dibuka...");
                      in.responTerminal("Koneksi Gagal, Error Pada : "+ucoe.getMessage());
                }
    	}
    	// Menambahkan Event Listener pada Serial Port
    	try {
      		port.addEventListener(new SerialPortEventListener() {
     			public void serialEvent(SerialPortEvent event) {
    				try {
      					// Apabila ada respons dari terminal, lakukan pembacaan
      					while ( (n = input.available()) > 0) 
      					{
        					n = input.read(bacaBuffer, bufferOffset, n);
        					bufferOffset += n;

        					// Jika ada respons "\15" (Line Feed Carriage Return),
        					if ( (bacaBuffer[bufferOffset - 1] == 10) &&
            					(bacaBuffer[bufferOffset - 2] == 13)) 
            				{
         		 				String buffer = new String(bacaBuffer, 0, bufferOffset - 2);
        		 				// Berikan ke methode terimaAT
         		 				terimaAT(buffer);
         		 				bufferOffset = 0;
       						} // Akhir if
      					} // Akhir while
    				} // Akhir try
    				catch (IOException e) 
    				{ }
  				}
      		});
    	} //Akhir try addEvenListener
    	catch (TooManyListenersException tmle) {
            if(in != null) {
                  in.responTerminal("Terjadi Kesalahan Pada : "+tmle);
            }
    	}
    
    
    }
  	
                    
    private void kirimAT(String atCmd, int delay) {
    	Boolean tungguDelay = new Boolean(true);
    	boolean getDelay = false;
    	// Membuat antrian proses
    	synchronized (tungguDelay) {
      		try 
      		{
        		// Menulis AT Commmand
        		output.write( (atCmd).getBytes());
        		output.flush();// Hapus OutputStream
      		} //Akhir Try
     		catch (IOException e) {}
      		try 
      		{
        		tungguDelay.wait(delay);
      		} // Akhir try
      		catch (InterruptedException ie) 
      		{
        		getDelay = true;
      		} // Akhir catch
    	} // Akhir syncronized
  	}
  	
    public void prosesKirimSms(String notlp, String pesan) {
    	try {
      		// Merubah pesan menjadi Format PDU (Protocol Data Unit)
      		String pesanPDUKirim = PduKirimSms(notlp.trim(), pesan.trim());
      		// Proses Mengirim Pesan
      		kirimAT("AT+CMGS=" + (pesanPDUKirim.length() / 2) + "\15", 500);
      		kirimAT("00" + pesanPDUKirim, 2500); // Kirim Pesan Format PDU
      		kirimAT("\032", 100); // Ctrl + Z
                
      		// Berikan waktu sleep agar terminal siap kembali untuk mengirim pesan
      		Thread.currentThread().sleep(15000); // aslinya 10000

    	} // Akhir try

    	catch (Exception e) {
            if(in != null) {
                  in.responTerminal("Error Mengirim Pesan : "+e);
            }
        }
    }
  	
    public void prosesTutup() {
        try {
                if(port != null)
                    port.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void setPort(String port) {
  	portName=port;
   }
    public void setBaudRate(int kec) {
  	nilaiBaud=kec;
   }    
}
