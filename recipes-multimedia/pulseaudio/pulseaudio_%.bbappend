# This should either patch pulseaudio to provide a pulseaudio-module-dev
# subpackage which then can be used to compile module-murphy-ivi, or
# patch the pulseaudio source tree to include and compile module-murphy-ivi.

SRC_URI_append = " \
   git://github.com/otcshare/pulseaudio.git;protocol=git;branch=tsd_10 \
"

SRC_URI_remove = " \
    http://freedesktop.org/software/pulseaudio/releases/${BP}.tar.xz \
    file://0001-padsp-Make-it-compile-on-musl.patch \
    file://0001-client-conf-Add-allow-autospawn-for-root.patch \
    file://pulseaudio-discuss-iochannel-don-t-use-variable-length-array-in-union.patch \
"

SRCREV = "e03027dfebf381da3a91e6f403b8f587c2dae772"

S = "${WORKDIR}/git"

do_configure_prepend () {
   mkdir -p ${S}/build-aux && touch ${S}/build-aux/config.rpath
}