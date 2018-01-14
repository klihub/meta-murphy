DESCRIPTION = "A Scriptable Resource Policy Daemon And Framework"
HOMEPAGE = "https://01.org/murphy/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE-BSD;md5=f9f435c1bd3a753365e799edf375fc42"

DEPENDS = "json-c lua dbus systemd libcheck"
PV = "2018.01"

SRC_URI = " \
    git://github.com/klihub/murphy.git;protocol=https \
"
SRCREV = "d829b717a0b96d5ecc99dfa96f5a56ea040c7a02"

S = "${WORKDIR}/git"

EXTRA_OECONF += " \
    --enable-dependency-tracking \
    --enable-extra-warnings \
    --enable-shared \
    --disable-static \
    --with-resources \
    --disable-qt \
    --disable-ecore \
    --without-documentation \
    --disable-smack \
"

PACKAGECONFIG ?= " \
    pulseaudio \
    glib \
    dbus \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'websockets', 'websockets','', d)} \
    console \
"

PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
PACKAGECONFIG[glib] = "--enable-glib,--disable-glib,glib-2.0"
PACKAGECONFIG[dbus] = "--enable-gpl --enable-libdbus,--disable-gpl --disable-libdbus,dbus"
PACKAGECONFIG[websockets] = "--enable-websockets,--disable-websockets,libwebsockets"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
PACKAGECONFIG[console] = "--enable-console,--disable-console"


inherit pkgconfig autotools systemd

EXTRA_OEMAKE = "V=1"

do_configure_prepend () {
    export CFLAGS="${CFLAGS}"
}

#do_install_append () {
#    rm -f ${D}${libdir}/murphy/libmdb.so
#    rm -f ${D}${libdir}/murphy/libmqi.so
#    rm -f ${D}${libdir}/murphy/libmql.so
#}
