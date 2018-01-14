FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://lua-5.3.4-sharedlib.patch \
"

# We want to enable module-compatibility as well so we need to modify
# EXTRA_OEMAKE. Unfortunately, I couldn't find a way to take the existing
# value, set in the base Lua recipe, and mangle it to include the extra
# defines we want without breaking the compilation. So we just set the
# EXTRA_OEMAKE from the base recipe pre-modified with our changes. This
# has unintended side-effects if someone modifies EXTRA_OEMAKE in the
# base recipe which we blindly overwrite here.
LUA_DEFINES = "-DLUA_USE_LINUX -DLUA_COMPAT_5_2 -DLUA_COMPAT_MODULE"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} ${LUA_DEFINES} -fPIC' MYLDFLAGS='${LDFLAGS}'"

do_install_append () {
    # Fix Cflags and redo installation.
    sed -e "s/@VERSION@/${PV}/;s/Cflags:/Cflags: ${LUA_DEFINES}/" ${WORKDIR}/lua.pc.in > ${WORKDIR}/lua.pc
    install -m 0644 ${WORKDIR}/lua.pc ${D}${libdir}/pkgconfig/
}
