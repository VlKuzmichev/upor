package rzd.zrw.upor.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_DISPATCHER,
    ROLE_AUDITOR,
    ROLE_BOSS,
    ROLE_ADMIN,
    ROLE_sUSER,
    ROLE_sDISPATCHER,
    ROLE_sAUDITOR,
    ROLE_sBOSS,
    ROLE_sADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}