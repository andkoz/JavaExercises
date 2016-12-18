package launcher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

public class SystemInfo {
	Map<String, Charset> charsets ;
	String defaultCharset ;
	String fileSeparator ;
	String userDir ;
	InetAddress localHostAddress ;
	static Properties sysProperties ;
	/*
	(Wywo³anie metody getSQLStateType interfejsu DatabaseMetaData pozwoli nam dowiedzieæ siê, 
	który standard jest u¿ywany przez dany sterownik
	SQLWarning w = stat.getWarning(); while (w != null) {   operacje na w   w = w.nextWarning(); } Klasa DataTruncation (bêd¹ca klas¹ pochodn¹ klasy SQLWarning) jest u¿ywana, gdy dane wczytane z bazy zosta³y nieoczekiwanie obciête. Jeœli sytuacja ta ma miejsce podczas wykonywania polecenia UPDATE, to obiekt klasy DataTruncation jest wyrzucany jako wyj¹tek.
 	for (Throwable t : sqlException) {   operacje na t } 
	
	*/
	
	public SystemInfo(){
		super() ;
		fileSeparator = java.io.File.separator ;
		userDir = System.getProperty("user.dir") ;
		charsets = Charset.availableCharsets();
		defaultCharset = Charset.defaultCharset().displayName();
			
		 try {
			localHostAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void getAvailableCharsets() {
		for (String name : charsets.keySet())
			System.out.println(name);
	}

	public static void main(String[] args) {
		
		SystemInfo sysInfo = new SystemInfo () ;
		
		sysInfo.getAvailableCharsets() ;
		System.out.println(sysInfo.localHostAddress);
		sysProperties = System.getProperties();
		sysProperties.list(System.out);
	}
	
}

/*
Big5
Big5-HKSCS
CESU-8
EUC-JP
EUC-KR
GB18030
GB2312
GBK
IBM-Thai
IBM00858
IBM01140
IBM01141
IBM01142
IBM01143
IBM01144
IBM01145
IBM01146
IBM01147
IBM01148
IBM01149
IBM037
IBM1026
IBM1047
IBM273
IBM277
IBM278
IBM280
IBM284
IBM285
IBM290
IBM297
IBM420
IBM424
IBM437
IBM500
IBM775
IBM850
IBM852
IBM855
IBM857
IBM860
IBM861
IBM862
IBM863
IBM864
IBM865
IBM866
IBM868
IBM869
IBM870
IBM871
IBM918
ISO-2022-CN
ISO-2022-JP
ISO-2022-JP-2
ISO-2022-KR
ISO-8859-1
ISO-8859-13
ISO-8859-15
ISO-8859-2
ISO-8859-3
ISO-8859-4
ISO-8859-5
ISO-8859-6
ISO-8859-7
ISO-8859-8
ISO-8859-9
JIS_X0201
JIS_X0212-1990
KOI8-R
KOI8-U
Shift_JIS
TIS-620
US-ASCII
UTF-16
UTF-16BE
UTF-16LE
UTF-32
UTF-32BE
UTF-32LE
UTF-8
windows-1250
windows-1251
windows-1252
windows-1253
windows-1254
windows-1255
windows-1256
windows-1257
windows-1258
windows-31j
x-Big5-HKSCS-2001
x-Big5-Solaris
x-euc-jp-linux
x-EUC-TW
x-eucJP-Open
x-IBM1006
x-IBM1025
x-IBM1046
x-IBM1097
x-IBM1098
x-IBM1112
x-IBM1122
x-IBM1123
x-IBM1124
x-IBM1166
x-IBM1364
x-IBM1381
x-IBM1383
x-IBM300
x-IBM33722
x-IBM737
x-IBM833
x-IBM834
x-IBM856
x-IBM874
x-IBM875
x-IBM921
x-IBM922
x-IBM930
x-IBM933
x-IBM935
x-IBM937
x-IBM939
x-IBM942
x-IBM942C
x-IBM943
x-IBM943C
x-IBM948
x-IBM949
x-IBM949C
x-IBM950
x-IBM964
x-IBM970
x-ISCII91
x-ISO-2022-CN-CNS
x-ISO-2022-CN-GB
x-iso-8859-11
x-JIS0208
x-JISAutoDetect
x-Johab
x-MacArabic
x-MacCentralEurope
x-MacCroatian
x-MacCyrillic
x-MacDingbat
x-MacGreek
x-MacHebrew
x-MacIceland
x-MacRoman
x-MacRomania
x-MacSymbol
x-MacThai
x-MacTurkish
x-MacUkraine
x-MS932_0213
x-MS950-HKSCS
x-MS950-HKSCS-XP
x-mswin-936
x-PCK
x-SJIS_0213
x-UTF-16LE-BOM
X-UTF-32BE-BOM
X-UTF-32LE-BOM
x-windows-50220
x-windows-50221
x-windows-874
x-windows-949
x-windows-950
x-windows-iso2022jp


*/

/*

-- listing properties --
java.runtime.name=Java(TM) SE Runtime Environment
sun.boot.library.path=C:\Program Files\Java\jre1.8.0_111\bin
java.vm.version=25.111-b14
java.vm.vendor=Oracle Corporation
java.vendor.url=http://java.oracle.com/
path.separator=;
java.vm.name=Java HotSpot(TM) Client VM
file.encoding.pkg=sun.io
user.script=
user.country=PL
sun.java.launcher=SUN_STANDARD
sun.os.patch.level=
java.vm.specification.name=Java Virtual Machine Specification
user.dir=C:\Dropbox\WORK\Eclipse\00-Praca-domo...
java.runtime.version=1.8.0_111-b14
java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment
java.endorsed.dirs=C:\Program Files\Java\jre1.8.0_111\li...
os.arch=x86
java.io.tmpdir=C:\Users\Jimi\AppData\Local\Temp\
line.separator=

java.vm.specification.vendor=Oracle Corporation
user.variant=
os.name=Windows 10
sun.jnu.encoding=Cp1250
java.library.path=C:\Program Files\Java\jre1.8.0_111\bi...
java.specification.name=Java Platform API Specification
java.class.version=52.0
sun.management.compiler=HotSpot Client Compiler
os.version=10.0
user.home=C:\Users\Jimi
user.timezone=
java.awt.printerjob=sun.awt.windows.WPrinterJob
file.encoding=Cp1250
java.specification.version=1.8
user.name=Jimi
java.class.path=C:\Dropbox\WORK\Eclipse\00-Praca-domo...
java.vm.specification.version=1.8
sun.arch.data.model=32
java.home=C:\Program Files\Java\jre1.8.0_111
sun.java.command=launcher.SystemInfo
java.specification.vendor=Oracle Corporation
user.language=pl
awt.toolkit=sun.awt.windows.WToolkit
java.vm.info=mixed mode
java.version=1.8.0_111
java.ext.dirs=C:\Program Files\Java\jre1.8.0_111\li...
sun.boot.class.path=C:\Program Files\Java\jre1.8.0_111\li...
java.vendor=Oracle Corporation
file.separator=\
java.vendor.url.bug=http://bugreport.sun.com/bugreport/
sun.cpu.endian=little
sun.io.unicode.encoding=UnicodeLittle
sun.desktop=windows
sun.cpu.isalist=pentium_pro+mmx pentium_pro pentium+m...


*/

