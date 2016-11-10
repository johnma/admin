package com.iware.lottery.admin;

/**
 * Created by johnma on 2016/11/2.
 */
public final class Constants {

    /**
     * prefix of REST API
     */
    public static final String URI_API = "/api";

    public static final String URI_IDENTITY = "/identity";

    public static final String URI_USERS = "/users";

    public static final String URI_COMMENTS = "/comments";

    private Constants() {
        throw new InstantiationError( "Must not instantiate this class" );
    }

}