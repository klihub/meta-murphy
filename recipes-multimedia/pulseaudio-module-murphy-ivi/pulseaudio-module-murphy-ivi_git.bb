DESCRIPTION = "PulseAudio Murphy Module"
HOMEPAGE = "https://01.org/murphy/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS = "pulseaudio murphy"
PV = "2018.01"

SRC_URI = " \
    git://github.com/juimonen/pulseaudio-module-murphy-ivi.git;protocol=https;branch=master \
"
SRCREV = "aa0960748350badb471bdac8d072ec2c94bb7fac"

S = "${WORKDIR}/git"

EXTRA_OECONF += " \
    --enable-murphy \
"

EXTRA_OEMAKE = "V=1"

FILES_${PN} = " \
    ${sysconfdir}/pulse/* \
    ${libdir}/pulse-10.0/modules/* \
"

RCONFLICTS_${PN} = " \
    pulseaudio-module-combine \
    pulseaudio-module-combine-sink \
    pulseaudio-module-augment-properties \
"


inherit pkgconfig autotools systemd


do_configure_prepend () {
    export CFLAGS="${CFLAGS}"
}
