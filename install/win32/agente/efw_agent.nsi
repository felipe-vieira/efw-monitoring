; Script generated by the HM NIS Edit Script Wizard.

; HM NIS Edit Wizard helper defines
!define PRODUCT_NAME "EFW Agente"
!define PRODUCT_VERSION "1.0"
!define PRODUCT_PUBLISHER "FIAP"
!define PRODUCT_WEB_SITE "https://github.com/felipe-vieira/efw-monitoring"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"
!define PRODUCT_STARTMENU_REGVAL "NSIS:StartMenuDir"

; MUI 1.67 compatible ------
!include "MUI.nsh"

; MUI Settings
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

; Language Selection Dialog Settings
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "NSIS:Language"

; Welcome page
!insertmacro MUI_PAGE_WELCOME
; License page
!insertmacro MUI_PAGE_LICENSE "efw-license.txt"
; Directory page
!insertmacro MUI_PAGE_DIRECTORY
; Start menu page
var ICONS_GROUP
!define MUI_STARTMENUPAGE_NODISABLE
!define MUI_STARTMENUPAGE_DEFAULTFOLDER "EFW Agente"
!define MUI_STARTMENUPAGE_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_STARTMENUPAGE_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_STARTMENUPAGE_REGISTRY_VALUENAME "${PRODUCT_STARTMENU_REGVAL}"
!insertmacro MUI_PAGE_STARTMENU Application $ICONS_GROUP
; Instfiles page
!insertmacro MUI_PAGE_INSTFILES
; Finish page
!insertmacro MUI_PAGE_FINISH

; Uninstaller pages
!insertmacro MUI_UNPAGE_INSTFILES

; Language files
!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "PortugueseBR"

; MUI end ------

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "efw-agent.exe"
InstallDir "$PROGRAMFILES\EFW Agente"
ShowInstDetails show
ShowUnInstDetails show

Function .onInit
  !insertmacro MUI_LANGDLL_DISPLAY
FunctionEnd

Section "MainSection" SEC01
  SetOutPath "$INSTDIR\bat"
  SetOverwrite try
  File "wrapper\bat\agente.jar"
  SetOutPath "$INSTDIR\bat\agente_lib"
  File "wrapper\bat\agente_lib\commons-beanutils-1.8.0.jar"
  File "wrapper\bat\agente_lib\commons-beanutils-bean-collections-1.8.0.jar"
  File "wrapper\bat\agente_lib\commons-beanutils-core-1.8.0.jar"
  File "wrapper\bat\agente_lib\commons-codec-1.6.jar"
  File "wrapper\bat\agente_lib\commons-collections-3.2.1.jar"
  File "wrapper\bat\agente_lib\commons-lang-2.5.jar"
  File "wrapper\bat\agente_lib\commons-logging-1.1.1.jar"
  File "wrapper\bat\agente_lib\commons-logging-adapters-1.1.1.jar"
  File "wrapper\bat\agente_lib\commons-logging-api-1.1.1.jar"
  File "wrapper\bat\agente_lib\ezmorph-1.0.6.jar"
  File "wrapper\bat\agente_lib\fluent-hc-4.2.jar"
  File "wrapper\bat\agente_lib\httpclient-4.2.jar"
  File "wrapper\bat\agente_lib\httpclient-cache-4.2.jar"
  File "wrapper\bat\agente_lib\httpcore-4.2.jar"
  File "wrapper\bat\agente_lib\httpmime-4.2.jar"
  File "wrapper\bat\agente_lib\jboss-as-controller-client-7.0.2.Final.jar"
  File "wrapper\bat\agente_lib\jboss-as-protocol-7.0.2.Final.jar"
  File "wrapper\bat\agente_lib\jboss-dmr-1.0.0.Final.jar"
  File "wrapper\bat\agente_lib\jboss-logging-3.0.1.GA.jar"
  File "wrapper\bat\agente_lib\jboss-marshalling-1.3.0.GA.jar"
  File "wrapper\bat\agente_lib\jboss-remoting-3.2.0.Beta2.jar"
  File "wrapper\bat\agente_lib\jboss-sasl-1.0.0.Beta2.jar"
  File "wrapper\bat\agente_lib\jboss-threads-2.0.0.GA.jar"
  File "wrapper\bat\agente_lib\json-lib-2.4-jdk15.jar"
  File "wrapper\bat\agente_lib\jtds-1.2.5.jar"
  File "wrapper\bat\agente_lib\junit-4.10.jar"
  File "wrapper\bat\agente_lib\libsigar-amd64-freebsd-6.so"
  File "wrapper\bat\agente_lib\libsigar-amd64-linux.so"
  File "wrapper\bat\agente_lib\libsigar-amd64-solaris.so"
  File "wrapper\bat\agente_lib\libsigar-ia64-hpux-11.sl"
  File "wrapper\bat\agente_lib\libsigar-ia64-linux.so"
  File "wrapper\bat\agente_lib\libsigar-pa-hpux-11.sl"
  File "wrapper\bat\agente_lib\libsigar-ppc-aix-5.so"
  File "wrapper\bat\agente_lib\libsigar-ppc-linux.so"
  File "wrapper\bat\agente_lib\libsigar-ppc64-aix-5.so"
  File "wrapper\bat\agente_lib\libsigar-ppc64-linux.so"
  File "wrapper\bat\agente_lib\libsigar-s390x-linux.so"
  File "wrapper\bat\agente_lib\libsigar-sparc-solaris.so"
  File "wrapper\bat\agente_lib\libsigar-sparc64-solaris.so"
  File "wrapper\bat\agente_lib\libsigar-universal-macosx.dylib"
  File "wrapper\bat\agente_lib\libsigar-universal64-macosx.dylib"
  File "wrapper\bat\agente_lib\libsigar-x86-freebsd-5.so"
  File "wrapper\bat\agente_lib\libsigar-x86-freebsd-6.so"
  File "wrapper\bat\agente_lib\libsigar-x86-linux.so"
  File "wrapper\bat\agente_lib\libsigar-x86-solaris.so"
  File "wrapper\bat\agente_lib\ojdbc14.jar"
  File "wrapper\bat\agente_lib\sigar-amd64-winnt.dll"
  File "wrapper\bat\agente_lib\sigar-x86-winnt.dll"
  File "wrapper\bat\agente_lib\sigar-x86-winnt.lib"
  File "wrapper\bat\agente_lib\sigar.jar"
  File "wrapper\bat\agente_lib\xnio-api-3.0.0.Beta3.jar"
  File "wrapper\bat\agente_lib\xnio-nio-3.0.0.Beta3.jar"
  SetOutPath "$INSTDIR\bat\demos"
  File "wrapper\bat\demos\installServicesManagerServer.bat"
  File "wrapper\bat\demos\jnlpDemo.bat"
  File "wrapper\bat\demos\remoteLaunchTomcatDemo.bat"
  File "wrapper\bat\demos\runScript.bat"
  File "wrapper\bat\demos\testMail.bat"
  SetOutPath "$INSTDIR\bat"
  File "wrapper\bat\genConfig.bat"
  File "wrapper\bat\installService.bat"
  File "wrapper\bat\queryService.bat"
  File "wrapper\bat\runConsole.bat"
  File "wrapper\bat\runConsoleW.bat"
  File "wrapper\bat\runHelloWorld.bat"
  File "wrapper\bat\runServicesManagerClient.bat"
  File "wrapper\bat\runServicesManagerServer.bat"
  File "wrapper\bat\setenv.bat"
  File "wrapper\bat\startService.bat"
  File "wrapper\bat\stopService.bat"
  File "wrapper\bat\systemTrayIcon.bat"
  File "wrapper\bat\sytemTrayIconW.bat"
  File "wrapper\bat\uninstallService.bat"
  File "wrapper\bat\wrapper.bat"
  File "wrapper\bat\wrapperW.bat"
  SetOutPath "$INSTDIR\conf\samples\luceneNutch"
  File "wrapper\conf\samples\luceneNutch\ReadMe.txt"
  File "wrapper\conf\samples\luceneNutch\wrapper.nutch_crawl.conf"
  File "wrapper\conf\samples\luceneNutch\wrapper.nutch_crawl_groovy.conf"
  File "wrapper\conf\samples\luceneNutch\wrapper.nutch_recrawl_groovy.conf"
  File "wrapper\conf\samples\luceneNutch\wrapper.nutch_recrawl_solr_groovy.conf"
  File "wrapper\conf\samples\luceneNutch\wrapper.nutch_tomcat.conf"
  SetOutPath "$INSTDIR\conf\samples"
  File "wrapper\conf\samples\ReadMe.txt"
  File "wrapper\conf\samples\tomcat.conf"
  File "wrapper\conf\samples\tomcat.stop.conf"
  File "wrapper\conf\samples\wrapper.activemq.conf"
  File "wrapper\conf\samples\wrapper.derby.conf"
  File "wrapper\conf\samples\wrapper.derby.stop.conf"
  File "wrapper\conf\samples\wrapper.equinox.conf"
  File "wrapper\conf\samples\wrapper.groovy_helloworld.conf"
  File "wrapper\conf\samples\wrapper.jar.conf"
  File "wrapper\conf\samples\wrapper.jboss.conf"
  File "wrapper\conf\samples\wrapper.jboss7.conf"
  File "wrapper\conf\samples\wrapper.jetty.conf"
  File "wrapper\conf\samples\wrapper.oracle.odi.conf"
  File "wrapper\conf\samples\wrapper.ping.conf"
  SetOutPath "$INSTDIR\conf"
  File "wrapper\conf\wrapper.conf"
  File "wrapper\conf\wrapper.conf.default"
  File "wrapper\conf\wrapper.javaws.conf"
  SetOutPath "$INSTDIR\lib\core\commons"
  File "wrapper\lib\core\commons\commons-cli-2-SNAPSHOT.jar"
  File "wrapper\lib\core\commons\commons-collections-3.2.jar"
  File "wrapper\lib\core\commons\commons-configuration-1.8.jar"
  File "wrapper\lib\core\commons\commons-io-1.3.1.jar"
  File "wrapper\lib\core\commons\commons-lang-2.4.jar"
  File "wrapper\lib\core\commons\commons-logging-1.1.jar"
  File "wrapper\lib\core\commons\commons-vfs2-2.0.jar"
  SetOutPath "$INSTDIR\lib\core\groovy"
  File "wrapper\lib\core\groovy\groovy-all-1.8.6.jar"
  SetOutPath "$INSTDIR\lib\core\jna"
  File "wrapper\lib\core\jna\jna-3.4.1.jar"
  File "wrapper\lib\core\jna\platform-3.4.1.jar"
  SetOutPath "$INSTDIR\lib\core\netty"
  File "wrapper\lib\core\netty\netty-3.5.1.Final.jar"
  SetOutPath "$INSTDIR\lib\core"
  File "wrapper\lib\core\ReadMe.txt"
  SetOutPath "$INSTDIR\lib\core\regex"
  File "wrapper\lib\core\regex\jrexx-1.1.1.jar"
  SetOutPath "$INSTDIR\lib\core\yajsw"
  File "wrapper\lib\core\yajsw\ahessian.jar"
  SetOutPath "$INSTDIR\lib\extended\abeille"
  File "wrapper\lib\extended\abeille\formsrt.jar"
  SetOutPath "$INSTDIR\lib\extended\commons"
  File "wrapper\lib\extended\commons\commons-codec-1.3.jar"
  File "wrapper\lib\extended\commons\commons-httpclient-3.0.1.jar"
  File "wrapper\lib\extended\commons\commons-net-1.4.1.jar"
  SetOutPath "$INSTDIR\lib\extended\glazedlists"
  File "wrapper\lib\extended\glazedlists\commons-beanutils-1.8.2.jar"
  File "wrapper\lib\extended\glazedlists\glazedlists-1.8.0_java15.jar"
  SetOutPath "$INSTDIR\lib\extended\jgoodies"
  File "wrapper\lib\extended\jgoodies\forms-1.2.0.jar"
  SetOutPath "$INSTDIR\lib\extended\quartz"
  File "wrapper\lib\extended\quartz\quartz-1.8.0.jar"
  SetOutPath "$INSTDIR\lib\extended"
  File "wrapper\lib\extended\ReadMe.txt"
  SetOutPath "$INSTDIR\lib\extended\velocity"
  File "wrapper\lib\extended\velocity\velocity-1.6.3.jar"
  SetOutPath "$INSTDIR\lib\extended\vfs-webdav"
  File "wrapper\lib\extended\vfs-webdav\jackrabbit-webdav-1.5.6.jar"
  File "wrapper\lib\extended\vfs-webdav\slf4j-api-1.5.0.jar"
  File "wrapper\lib\extended\vfs-webdav\slf4j-jdk14-1.5.0.jar"
  File "wrapper\lib\extended\vfs-webdav\xercesImpl.jar"
  SetOutPath "$INSTDIR\lib\extended\yajsw"
  File "wrapper\lib\extended\yajsw\hessian4.jar"
  File "wrapper\lib\extended\yajsw\srvmgr.jar"
  SetOutPath "$INSTDIR\lib\groovy\joda"
  File "wrapper\lib\groovy\joda\joda-time-1.6.jar"
  SetOutPath "$INSTDIR\lib\groovy\mail"
  File "wrapper\lib\groovy\mail\activation.jar"
  File "wrapper\lib\groovy\mail\mail.jar"
  SetOutPath "$INSTDIR\lib\groovy"
  File "wrapper\lib\groovy\ReadMe.txt"
  SetOutPath "$INSTDIR\lib\groovy\snmp"
  File "wrapper\lib\groovy\snmp\SNMP4J.jar"
  SetOutPath "$INSTDIR\log"
  File "wrapper\log\wrapper.log"
  File "wrapper\log\wrapper.log.lck"
  SetOutPath "$INSTDIR"
  File "wrapper\release_notes.txt"
  SetOutPath "$INSTDIR\scripts"
  File "wrapper\scripts\cluster.gv"
  File "wrapper\scripts\commandCondition.gv"
  File "wrapper\scripts\decryptor.gv"
  SetOutPath "$INSTDIR\scripts\equinox"
  File "wrapper\scripts\equinox\equinoxShutdown.gv"
  SetOutPath "$INSTDIR\scripts"
  File "wrapper\scripts\fileCondition.gv"
  SetOutPath "$INSTDIR\scripts\groovy"
  File "wrapper\scripts\groovy\helloworld.gv"
  File "wrapper\scripts\groovy\ReadMe.txt"
  SetOutPath "$INSTDIR\scripts"
  File "wrapper\scripts\linearRestartDelay.gv"
  File "wrapper\scripts\mapNetworkDrive.gv"
  File "wrapper\scripts\maxDuration.gv"
  File "wrapper\scripts\maxStartup.gv"
  SetOutPath "$INSTDIR\scripts\nutch"
  File "wrapper\scripts\nutch\nutch_base.gv"
  File "wrapper\scripts\nutch\nutch_crawl.gv"
  File "wrapper\scripts\nutch\nutch_recrawl.gv"
  File "wrapper\scripts\nutch\nutch_solr.gv"
  File "wrapper\scripts\nutch\ReadMe.txt"
  SetOutPath "$INSTDIR\scripts"
  File "wrapper\scripts\ReadMe.txt"
  File "wrapper\scripts\sendMail.gv"
  File "wrapper\scripts\setenv.gv"
  File "wrapper\scripts\snmpTrap.gv"
  File "wrapper\scripts\threadDump.gv"
  File "wrapper\scripts\timeCondition.gv"
  File "wrapper\scripts\trayColor.gv"
  File "wrapper\scripts\trayMessage.gv"
  SetOutPath "$INSTDIR\templates"
  File "wrapper\templates\daemon.vm"
  File "wrapper\templates\launchd.plist.vm"
  SetOutPath "$INSTDIR"
  File "wrapper\wmic.tmp"
  File "wrapper\wrapper.jar"
  File "wrapper\wrapperApp.jar"
  File "wrapper\yajsw.policy.txt"
  ExecWait "$INSTDIR\bat\installService.bat"
  ExecWait "$INSTDIR\bat\startService.bat"

; Shortcuts
  !insertmacro MUI_STARTMENU_WRITE_BEGIN Application

  !insertmacro MUI_STARTMENU_WRITE_END
SectionEnd

Section -AdditionalIcons
  !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
  CreateDirectory "$SMPROGRAMS\$ICONS_GROUP"
    CreateShortCut "$STARTMENU\$ICONS_GROUP\Iniciar Agente EFW.lnk" "$INSTDIR\bat\startService.bat"
  CreateShortCut "$STARTMENU\$ICONS_GROUP\Parar Agente EFW.lnk" "$INSTDIR\bat\stopService.bat"
  CreateShortCut "$SMPROGRAMS\$ICONS_GROUP\Uninstall.lnk" "$INSTDIR\uninst.exe"
  !insertmacro MUI_STARTMENU_WRITE_END
SectionEnd

Section -Post
  WriteUninstaller "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "URLInfoAbout" "${PRODUCT_WEB_SITE}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
SectionEnd


Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "$(^Name) was successfully removed from your computer."
FunctionEnd

Function un.onInit
!insertmacro MUI_UNGETLANGUAGE
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "Are you sure you want to completely remove $(^Name) and all of its components?" IDYES +2
  Abort
FunctionEnd

Section Uninstall
  !insertmacro MUI_STARTMENU_GETFOLDER "Application" $ICONS_GROUP
  ExecWait "$INSTDIR\bat\stopService.bat"
  ExecWait "$INSTDIR\bat\uninstallService.bat"
  Delete "$INSTDIR\uninst.exe"
  Delete "$INSTDIR\yajsw.policy.txt"
  Delete "$INSTDIR\wrapperApp.jar"
  Delete "$INSTDIR\wrapper.jar"
  Delete "$INSTDIR\wmic.tmp"
  Delete "$INSTDIR\tmp\out_2876393579737246782$$1352665157507"
  Delete "$INSTDIR\tmp\err_2876393579737246782$$1352665157507"
  Delete "$INSTDIR\templates\launchd.plist.vm"
  Delete "$INSTDIR\templates\daemon.vm"
  Delete "$INSTDIR\scripts\trayMessage.gv"
  Delete "$INSTDIR\scripts\trayColor.gv"
  Delete "$INSTDIR\scripts\timeCondition.gv"
  Delete "$INSTDIR\scripts\threadDump.gv"
  Delete "$INSTDIR\scripts\snmpTrap.gv"
  Delete "$INSTDIR\scripts\setenv.gv"
  Delete "$INSTDIR\scripts\sendMail.gv"
  Delete "$INSTDIR\scripts\ReadMe.txt"
  Delete "$INSTDIR\scripts\nutch\ReadMe.txt"
  Delete "$INSTDIR\scripts\nutch\nutch_solr.gv"
  Delete "$INSTDIR\scripts\nutch\nutch_recrawl.gv"
  Delete "$INSTDIR\scripts\nutch\nutch_crawl.gv"
  Delete "$INSTDIR\scripts\nutch\nutch_base.gv"
  Delete "$INSTDIR\scripts\maxStartup.gv"
  Delete "$INSTDIR\scripts\maxDuration.gv"
  Delete "$INSTDIR\scripts\mapNetworkDrive.gv"
  Delete "$INSTDIR\scripts\linearRestartDelay.gv"
  Delete "$INSTDIR\scripts\groovy\ReadMe.txt"
  Delete "$INSTDIR\scripts\groovy\helloworld.gv"
  Delete "$INSTDIR\scripts\fileCondition.gv"
  Delete "$INSTDIR\scripts\equinox\equinoxShutdown.gv"
  Delete "$INSTDIR\scripts\decryptor.gv"
  Delete "$INSTDIR\scripts\commandCondition.gv"
  Delete "$INSTDIR\scripts\cluster.gv"
  Delete "$INSTDIR\release_notes.txt"
  Delete "$INSTDIR\log\wrapper.log.lck"
  Delete "$INSTDIR\log\wrapper.log"
  Delete "$INSTDIR\lib\groovy\snmp\SNMP4J.jar"
  Delete "$INSTDIR\lib\groovy\ReadMe.txt"
  Delete "$INSTDIR\lib\groovy\mail\mail.jar"
  Delete "$INSTDIR\lib\groovy\mail\activation.jar"
  Delete "$INSTDIR\lib\groovy\joda\joda-time-1.6.jar"
  Delete "$INSTDIR\lib\extended\yajsw\srvmgr.jar"
  Delete "$INSTDIR\lib\extended\yajsw\hessian4.jar"
  Delete "$INSTDIR\lib\extended\vfs-webdav\xercesImpl.jar"
  Delete "$INSTDIR\lib\extended\vfs-webdav\slf4j-jdk14-1.5.0.jar"
  Delete "$INSTDIR\lib\extended\vfs-webdav\slf4j-api-1.5.0.jar"
  Delete "$INSTDIR\lib\extended\vfs-webdav\jackrabbit-webdav-1.5.6.jar"
  Delete "$INSTDIR\lib\extended\velocity\velocity-1.6.3.jar"
  Delete "$INSTDIR\lib\extended\ReadMe.txt"
  Delete "$INSTDIR\lib\extended\quartz\quartz-1.8.0.jar"
  Delete "$INSTDIR\lib\extended\jgoodies\forms-1.2.0.jar"
  Delete "$INSTDIR\lib\extended\glazedlists\glazedlists-1.8.0_java15.jar"
  Delete "$INSTDIR\lib\extended\glazedlists\commons-beanutils-1.8.2.jar"
  Delete "$INSTDIR\lib\extended\commons\commons-net-1.4.1.jar"
  Delete "$INSTDIR\lib\extended\commons\commons-httpclient-3.0.1.jar"
  Delete "$INSTDIR\lib\extended\commons\commons-codec-1.3.jar"
  Delete "$INSTDIR\lib\extended\abeille\formsrt.jar"
  Delete "$INSTDIR\lib\core\yajsw\ahessian.jar"
  Delete "$INSTDIR\lib\core\regex\jrexx-1.1.1.jar"
  Delete "$INSTDIR\lib\core\ReadMe.txt"
  Delete "$INSTDIR\lib\core\netty\netty-3.5.1.Final.jar"
  Delete "$INSTDIR\lib\core\jna\platform-3.4.1.jar"
  Delete "$INSTDIR\lib\core\jna\jna-3.4.1.jar"
  Delete "$INSTDIR\lib\core\groovy\groovy-all-1.8.6.jar"
  Delete "$INSTDIR\lib\core\commons\commons-vfs2-2.0.jar"
  Delete "$INSTDIR\lib\core\commons\commons-logging-1.1.jar"
  Delete "$INSTDIR\lib\core\commons\commons-lang-2.4.jar"
  Delete "$INSTDIR\lib\core\commons\commons-io-1.3.1.jar"
  Delete "$INSTDIR\lib\core\commons\commons-configuration-1.8.jar"
  Delete "$INSTDIR\lib\core\commons\commons-collections-3.2.jar"
  Delete "$INSTDIR\lib\core\commons\commons-cli-2-SNAPSHOT.jar"
  Delete "$INSTDIR\conf\wrapper.javaws.conf"
  Delete "$INSTDIR\conf\wrapper.conf.default"
  Delete "$INSTDIR\conf\wrapper.conf"
  Delete "$INSTDIR\conf\samples\wrapper.ping.conf"
  Delete "$INSTDIR\conf\samples\wrapper.oracle.odi.conf"
  Delete "$INSTDIR\conf\samples\wrapper.jetty.conf"
  Delete "$INSTDIR\conf\samples\wrapper.jboss7.conf"
  Delete "$INSTDIR\conf\samples\wrapper.jboss.conf"
  Delete "$INSTDIR\conf\samples\wrapper.jar.conf"
  Delete "$INSTDIR\conf\samples\wrapper.groovy_helloworld.conf"
  Delete "$INSTDIR\conf\samples\wrapper.equinox.conf"
  Delete "$INSTDIR\conf\samples\wrapper.derby.stop.conf"
  Delete "$INSTDIR\conf\samples\wrapper.derby.conf"
  Delete "$INSTDIR\conf\samples\wrapper.activemq.conf"
  Delete "$INSTDIR\conf\samples\tomcat.stop.conf"
  Delete "$INSTDIR\conf\samples\tomcat.conf"
  Delete "$INSTDIR\conf\samples\ReadMe.txt"
  Delete "$INSTDIR\conf\samples\luceneNutch\wrapper.nutch_tomcat.conf"
  Delete "$INSTDIR\conf\samples\luceneNutch\wrapper.nutch_recrawl_solr_groovy.conf"
  Delete "$INSTDIR\conf\samples\luceneNutch\wrapper.nutch_recrawl_groovy.conf"
  Delete "$INSTDIR\conf\samples\luceneNutch\wrapper.nutch_crawl_groovy.conf"
  Delete "$INSTDIR\conf\samples\luceneNutch\wrapper.nutch_crawl.conf"
  Delete "$INSTDIR\conf\samples\luceneNutch\ReadMe.txt"
  Delete "$INSTDIR\bat\wrapperW.bat"
  Delete "$INSTDIR\bat\wrapper.bat"
  Delete "$INSTDIR\bat\uninstallService.bat"
  Delete "$INSTDIR\bat\sytemTrayIconW.bat"
  Delete "$INSTDIR\bat\systemTrayIcon.bat"
  Delete "$INSTDIR\bat\stopService.bat"
  Delete "$INSTDIR\bat\startService.bat"
  Delete "$INSTDIR\bat\setenv.bat"
  Delete "$INSTDIR\bat\runServicesManagerServer.bat"
  Delete "$INSTDIR\bat\runServicesManagerClient.bat"
  Delete "$INSTDIR\bat\runHelloWorld.bat"
  Delete "$INSTDIR\bat\runConsoleW.bat"
  Delete "$INSTDIR\bat\runConsole.bat"
  Delete "$INSTDIR\bat\queryService.bat"
  Delete "$INSTDIR\bat\installService.bat"
  Delete "$INSTDIR\bat\genConfig.bat"
  Delete "$INSTDIR\bat\demos\testMail.bat"
  Delete "$INSTDIR\bat\demos\runScript.bat"
  Delete "$INSTDIR\bat\demos\remoteLaunchTomcatDemo.bat"
  Delete "$INSTDIR\bat\demos\jnlpDemo.bat"
  Delete "$INSTDIR\bat\demos\installServicesManagerServer.bat"
  Delete "$INSTDIR\bat\agente_lib\xnio-nio-3.0.0.Beta3.jar"
  Delete "$INSTDIR\bat\agente_lib\xnio-api-3.0.0.Beta3.jar"
  Delete "$INSTDIR\bat\agente_lib\sigar.jar"
  Delete "$INSTDIR\bat\agente_lib\sigar-x86-winnt.lib"
  Delete "$INSTDIR\bat\agente_lib\sigar-x86-winnt.dll"
  Delete "$INSTDIR\bat\agente_lib\sigar-amd64-winnt.dll"
  Delete "$INSTDIR\bat\agente_lib\ojdbc14.jar"
  Delete "$INSTDIR\bat\agente_lib\libsigar-x86-solaris.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-x86-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-x86-freebsd-6.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-x86-freebsd-5.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-universal64-macosx.dylib"
  Delete "$INSTDIR\bat\agente_lib\libsigar-universal-macosx.dylib"
  Delete "$INSTDIR\bat\agente_lib\libsigar-sparc64-solaris.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-sparc-solaris.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-s390x-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ppc64-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ppc64-aix-5.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ppc-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ppc-aix-5.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-pa-hpux-11.sl"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ia64-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-ia64-hpux-11.sl"
  Delete "$INSTDIR\bat\agente_lib\libsigar-amd64-solaris.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-amd64-linux.so"
  Delete "$INSTDIR\bat\agente_lib\libsigar-amd64-freebsd-6.so"
  Delete "$INSTDIR\bat\agente_lib\junit-4.10.jar"
  Delete "$INSTDIR\bat\agente_lib\jtds-1.2.5.jar"
  Delete "$INSTDIR\bat\agente_lib\json-lib-2.4-jdk15.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-threads-2.0.0.GA.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-sasl-1.0.0.Beta2.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-remoting-3.2.0.Beta2.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-marshalling-1.3.0.GA.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-logging-3.0.1.GA.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-dmr-1.0.0.Final.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-as-protocol-7.0.2.Final.jar"
  Delete "$INSTDIR\bat\agente_lib\jboss-as-controller-client-7.0.2.Final.jar"
  Delete "$INSTDIR\bat\agente_lib\httpmime-4.2.jar"
  Delete "$INSTDIR\bat\agente_lib\httpcore-4.2.jar"
  Delete "$INSTDIR\bat\agente_lib\httpclient-cache-4.2.jar"
  Delete "$INSTDIR\bat\agente_lib\httpclient-4.2.jar"
  Delete "$INSTDIR\bat\agente_lib\fluent-hc-4.2.jar"
  Delete "$INSTDIR\bat\agente_lib\ezmorph-1.0.6.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-logging-api-1.1.1.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-logging-adapters-1.1.1.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-logging-1.1.1.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-lang-2.5.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-collections-3.2.1.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-codec-1.6.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-beanutils-core-1.8.0.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-beanutils-bean-collections-1.8.0.jar"
  Delete "$INSTDIR\bat\agente_lib\commons-beanutils-1.8.0.jar"
  Delete "$INSTDIR\bat\agente.jar"

  Delete "$SMPROGRAMS\$ICONS_GROUP\Uninstall.lnk"
  Delete "$SMPROGRAMS\$ICONS_GROUP\Parar Agente EFW.lnk"
  Delete "$SMPROGRAMS\$ICONS_GROUP\Iniciar Agente EFW.lnk"

  RMDir "$SMPROGRAMS\$ICONS_GROUP"
  RMDir "$INSTDIR\tmp"
  RMDir "$INSTDIR\templates"
  RMDir "$INSTDIR\scripts\nutch"
  RMDir "$INSTDIR\scripts\groovy"
  RMDir "$INSTDIR\scripts\equinox"
  RMDir "$INSTDIR\scripts"
  RMDir "$INSTDIR\log"
  RMDir "$INSTDIR\lib\groovy\snmp"
  RMDir "$INSTDIR\lib\groovy\mail"
  RMDir "$INSTDIR\lib\groovy\joda"
  RMDir "$INSTDIR\lib\groovy"
  RMDir "$INSTDIR\lib\extended\yajsw"
  RMDir "$INSTDIR\lib\extended\vfs-webdav"
  RMDir "$INSTDIR\lib\extended\velocity"
  RMDir "$INSTDIR\lib\extended\quartz"
  RMDir "$INSTDIR\lib\extended\jgoodies"
  RMDir "$INSTDIR\lib\extended\glazedlists"
  RMDir "$INSTDIR\lib\extended\commons"
  RMDir "$INSTDIR\lib\extended\abeille"
  RMDir "$INSTDIR\lib\extended"
  RMDir "$INSTDIR\lib\core\yajsw"
  RMDir "$INSTDIR\lib\core\regex"
  RMDir "$INSTDIR\lib\core\netty"
  RMDir "$INSTDIR\lib\core\jna"
  RMDir "$INSTDIR\lib\core\groovy"
  RMDir "$INSTDIR\lib\core\commons"
  RMDir "$INSTDIR\lib\core"
  RMDir "$INSTDIR\conf\samples\luceneNutch"
  RMDir "$INSTDIR\conf\samples"
  RMDir "$INSTDIR\conf"
  RMDir "$INSTDIR\bat\demos"
  RMDir "$INSTDIR\bat\agente_lib"
  RMDir "$INSTDIR\bat"
  RMDir "$INSTDIR"

  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
  SetAutoClose true
SectionEnd