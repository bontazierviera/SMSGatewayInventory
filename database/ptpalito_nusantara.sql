# Host: localhost  (Version: 5.1.37)
# Date: 2015-02-11 19:19:25
# Generator: MySQL-Front 5.2  (Build 5.66)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='Asia/Krasnoyarsk' */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

DROP DATABASE IF EXISTS `ptpalito_nusantara`;
CREATE DATABASE `ptpalito_nusantara` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ptpalito_nusantara`;

#
# Source for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id_admin` varchar(15) NOT NULL DEFAULT '',
  `username` varchar(30) NOT NULL DEFAULT '',
  `nm_admin` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `alamat` varchar(125) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "admin"
#

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('AD0001','bonta','bonta zirviera','bonta','jl.h.yamin','08999199'),('AD0002','imey','suka indah mer yanta','************','jl.posdasd','622188888888'),('AD0003','cencen','Andreas','cencen','Ciputat','6289601808083');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

#
# Source for table "balas_req"
#

DROP TABLE IF EXISTS `balas_req`;
CREATE TABLE `balas_req` (
  `no_balas_req` varchar(13) NOT NULL DEFAULT '',
  `tgl_sms` date DEFAULT NULL,
  `waktu_sms` time DEFAULT NULL,
  `isi_sms` varchar(160) DEFAULT NULL,
  `no_sms_req` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_balas_req`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "balas_req"
#

/*!40000 ALTER TABLE `balas_req` DISABLE KEYS */;
INSERT INTO `balas_req` VALUES ('O0001','2015-01-26','12:03:05','Jumlah stok barang BG001 adalah 342 buah','628999199509'),('O0002','2015-01-26','12:05:59','Info ID Barang adalah : BG001, BG002, BG003, BG004, .','628999199509'),('O0003','2015-01-26','13:26:15','Maaf, Anda Bukan Karyawan PT.Palito Nusantara! Anda Tidak Dapat Menggunakan Program Ini.','628970802018'),('O0004','2015-01-26','13:59:05','INFO Barang dengan ID: BG001 adalah namaBrg=gula, qty=342.','628999199509'),('O0005','2015-01-26','14:06:40','Info ID Barang adalah : BG001, BG002, BG003, BG004, .','628999199509'),('O0006','2015-01-27','23:36:02','No. PO: P00001\0 belum terdaftar !','628999199509'),('O0007','2015-01-27','23:36:02','No. PO: P00001\0 belum terdaftar !','628999199509'),('O0008','2015-01-27','23:36:02','No. PO: P00001\0 belum terdaftar !','628999199509'),('O0009','2015-01-27','23:36:02','No. PO: P00001\0 belum terdaftar !','628999199509'),('O0010','2015-01-27','23:37:09','No. PO: P00001\0 belum terdaftar !','628999199509'),('O0011','2015-01-30','00:07:35','Maaf,Anda Bukan Karyawan PT.Palito Nusantara Atau Anda Belum Terdaftar! \nSilahkan Ketik : REG<spasi>IDKRYWN','628999199509'),('O0012','2015-01-30','00:08:16','Terima Kasih Anda telah bergabung besama kami. No Registrasi Anda adl R0001dan ID Karyawan Anda: ','628999199509'),('O0013','2015-01-30','08:12:29','Maaf,Anda Bukan Karyawan PT.Palito Nusantara Atau Anda Belum Terdaftar! \nSilahkan Ketik : REG<spasi>IDKRYWN','628999199509'),('O0014','2015-01-30','08:13:13','Terima Kasih Anda dengan ID Karyawan: K0001 ,sudah melakukan proses RegistrasiKetik UNREG untuk keluar registrasi','628999199509'),('O0015','2015-01-30','08:13:57','ID Barang: BG001 dengan quantity: 300 buah','628999199509'),('O0016','2015-01-30','08:14:41','Format SMS anda salah. Silahkan ketik : INFO untuk mengetahui Format SMS yang benar.','628999199509'),('O0017','2015-01-30','08:32:48','Maaf,Anda Bukan Karyawan PT.Palito Nusantara Atau Anda Belum Terdaftar! \nSilahkan Ketik : REG<spasi>IDKRYWN','628999199509'),('O0018','2015-01-30','08:33:32','Terima Kasih Anda dengan ID Karyawan: K0001 ,sudah melakukan proses Registrasi Ketik UNREG untuk keluar registrasi','628999199509'),('O0019','2015-01-30','08:50:20','Maaf,Anda Bukan Karyawan PT.Palito Nusantara Atau Anda Belum Terdaftar! \nSilahkan Ketik : REG<spasi>IDKRYWN','628999199509'),('O0020','2015-01-30','08:51:09','ID Barang: BG001 dengan quantity: 300 buah','628999199509'),('O0021','2015-02-02','14:36:01','Terima Kasih, Anda sudah tidak terdaftar di Sistem Kami. \nSilahkan Ketik REG<spasi>IDKRYWN untuk Mendaftar Kembali.','628999199509'),('O0022','2015-02-02','14:38:40','Terima Kasih, Anda sudah tidak terdaftar di Sistem Kami. \nSilahkan Ketik REG<spasi>IDKRYWN untuk Mendaftar Kembali.','628999199509');
/*!40000 ALTER TABLE `balas_req` ENABLE KEYS */;

#
# Source for table "barang"
#

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `id_brg` varchar(5) NOT NULL DEFAULT '',
  `nama_brg` varchar(30) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "barang"
#

INSERT INTO `barang` VALUES ('BG001','gula',300),('BG002','mentega',88),('BG003','baking powder',999),('BG004','garam',23);

#
# Source for table "broadcast"
#

DROP TABLE IF EXISTS `broadcast`;
CREATE TABLE `broadcast` (
  `no_broadcast` varchar(15) NOT NULL DEFAULT '',
  `isi_sms` varchar(160) DEFAULT NULL,
  `tgl_sms` date DEFAULT NULL,
  `waktu_sms` time DEFAULT NULL,
  `id_karyawan` varchar(7) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_broadcast`,`id_karyawan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "broadcast"
#

/*!40000 ALTER TABLE `broadcast` DISABLE KEYS */;
INSERT INTO `broadcast` VALUES ('B0001','tes BC sms Server','2015-01-25','22:04:39','K0003'),('B0002','tes BC sms Server','2015-01-25','22:04:58','K0001'),('B0003','tes bc','2015-01-26','14:15:25','K0003'),('B0004','tes bc','2015-01-26','14:15:44','K0001'),('B0005','tes bc','2015-01-26','15:53:39','K0001'),('B0006','tes broadcast','2015-01-27','09:01:02','K0001'),('B0007','tes bc','2015-01-27','09:01:53','K0001'),('B0008','tes bc','2015-01-27','09:03:43','K0001'),('B0009','tes bc ini','2015-01-27','09:04:21','K0001');
/*!40000 ALTER TABLE `broadcast` ENABLE KEYS */;

#
# Source for table "det_po"
#

DROP TABLE IF EXISTS `det_po`;
CREATE TABLE `det_po` (
  `no_po` varchar(10) NOT NULL DEFAULT '',
  `id_brg` varchar(5) NOT NULL DEFAULT '',
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_po`,`id_brg`),
  KEY `FK_Barang` (`id_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Data for table "det_po"
#

INSERT INTO `det_po` VALUES ('P00001','BG001',1000),('P00002','BG002',6000),('P00003','BG003',1122),('P00004','BG004',4000),('P00005','BG001',100),('P00006','BG003',2000),('P00007','BG002',1000);

#
# Source for table "karyawan"
#

DROP TABLE IF EXISTS `karyawan`;
CREATE TABLE `karyawan` (
  `id_karyawan` varchar(5) NOT NULL DEFAULT '',
  `nama` varchar(40) DEFAULT NULL,
  `alamat` varchar(125) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `jabatan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_karyawan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "karyawan"
#

/*!40000 ALTER TABLE `karyawan` DISABLE KEYS */;
INSERT INTO `karyawan` VALUES ('K0001','Bonta','jl.bintaro','628999199509','Staff'),('K0002','Imey','jl.Ciledug','62818637440','Staff'),('K0003','Andreas','jl.H.yamin','6289601838380','Staff');
/*!40000 ALTER TABLE `karyawan` ENABLE KEYS */;

#
# Source for table "po"
#

DROP TABLE IF EXISTS `po`;
CREATE TABLE `po` (
  `no_po` varchar(10) NOT NULL DEFAULT '',
  `tgl_po` date DEFAULT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `nama_brg` varchar(30) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `id_supplier` varchar(5) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_po`,`id_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "po"
#

INSERT INTO `po` VALUES ('P00001','2014-05-06','2015-12-03','gula',342,'S0001'),('P00002','2014-12-12','2015-12-13','mentega',88,'S0001'),('P00003','2015-01-01','2015-01-13','baking powder',33,'S0004'),('P00004','2015-01-09','2015-01-27','garam',44,'S0004'),('P00005','2015-01-01','2015-01-01','gula',1,'S0001'),('P00006','2015-01-27','2015-01-30','baking powder',12,'S0001'),('P00007','2015-01-27','2015-01-27','mentega',11,'S0002');

#
# Source for table "registrasi"
#

DROP TABLE IF EXISTS `registrasi`;
CREATE TABLE `registrasi` (
  `id_registrasi` varchar(7) NOT NULL DEFAULT '',
  `waktu_registrasi` time DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `tgl_registrasi` date DEFAULT NULL,
  `id_karyawan` varchar(5) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_registrasi`,`id_karyawan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

#
# Data for table "registrasi"
#

/*!40000 ALTER TABLE `registrasi` DISABLE KEYS */;
INSERT INTO `registrasi` VALUES ('R0001','08:57:38','628999199509','2015-01-30','K0001');
/*!40000 ALTER TABLE `registrasi` ENABLE KEYS */;

#
# Source for table "sms_req"
#

DROP TABLE IF EXISTS `sms_req`;
CREATE TABLE `sms_req` (
  `no_sms_req` varchar(15) NOT NULL DEFAULT '',
  `judul` varchar(50) DEFAULT NULL,
  `tgl_sms` date DEFAULT NULL,
  `waktu_sms` time DEFAULT NULL,
  `isi_sms` varchar(160) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `id_karyawan` varchar(7) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_sms_req`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "sms_req"
#

/*!40000 ALTER TABLE `sms_req` DISABLE KEYS */;
INSERT INTO `sms_req` VALUES ('I0001','SALAH FORMAT','2015-01-26','09:00:02','JDWLMASUK P00001','628999199509','K0001'),('I0002','SALAH FORMAT','2015-01-26','09:06:42','JADWAL P00001','628999199509','K0001'),('I0003','INFO','2015-01-26','09:10:24','INFO','628999199509','K0001'),('I0004','INFO','2015-01-26','09:13:50','INFO','628999199509','K0001'),('I0005','INFO','2015-01-26','09:13:50','INFO','628999199509','K0001'),('I0006','DETAIL PO','2015-01-27','23:36:02','DETAILPO P00001\0','628999199509','K0001'),('I0007','DETAIL PO','2015-01-27','23:36:02','DETAILPO P00001\0','628999199509','K0001'),('I0008','DETAIL PO','2015-01-27','23:36:02','DETAILPO P00001\0','628999199509','K0001'),('I0009','DETAIL PO','2015-01-27','23:36:02','DETAILPO P00001\0','628999199509','K0001'),('I0010','DETAIL PO','2015-01-27','23:37:09','DETAILPO P00001\0','628999199509','K0001'),('I0011','BELUM TERDAFTAR','2015-01-30','00:07:35','STOK BG001','628999199509',''),('I0012','REGISTRASI','2015-01-30','00:08:16','REG K0001','628999199509',''),('I0013','BELUM TERDAFTAR','2015-01-30','08:12:29','STOK BG001','628999199509',''),('I0014','REGISTRASI','2015-01-30','08:13:13','REG K0001','628999199509',''),('I0015','STOK','2015-01-30','08:13:57','STOK BG001','628999199509',''),('I0016','SALAH FORMAT','2015-01-30','08:14:41','UNREG','628999199509',''),('I0017','BELUM TERDAFTAR','2015-01-30','08:32:48','STOK BG001','628999199509',''),('I0018','REGISTRASI','2015-01-30','08:33:32','REG K0001','628999199509',''),('I0019','BELUM TERDAFTAR','2015-01-30','08:50:20','JDWLMASUK P00002','628999199509',''),('I0020','STOK','2015-01-30','08:51:09','STOK BG001','628999199509',''),('I0021','UNREGISTER','2015-02-02','14:36:01','UNREG R0001','628999199509','K0001'),('I0022','UNREGISTER','2015-02-02','14:38:40','UNREG R0001','628999199509','K0001');
/*!40000 ALTER TABLE `sms_req` ENABLE KEYS */;

#
# Source for table "supplier"
#

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id_supplier` char(5) NOT NULL DEFAULT '',
  `nama_supplier` varchar(40) DEFAULT NULL,
  `alamat` varchar(125) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "supplier"
#

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('S0001','boga sari','jl.ciledug','0219922211'),('S0002','ABC','jl.Kebon Jeruk','021559922'),('S0003','Craft','jl.Kebayoran Lama','021453663'),('S0004','Blue Band','jl.Pertukangan','0218882211');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;

#
#  Foreign keys for table det_po
#

ALTER TABLE `det_po`
ADD CONSTRAINT `FK_Barang` FOREIGN KEY (`id_brg`) REFERENCES `barang` (`id_brg`),
ADD CONSTRAINT `FK_PO` FOREIGN KEY (`no_po`) REFERENCES `po` (`no_po`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
