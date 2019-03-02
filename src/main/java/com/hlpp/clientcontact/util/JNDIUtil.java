
package com.hlpp.clientcontact.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class JNDIUtil {

    public static Context getInitialContext() throws NamingException {
        return new InitialContext();
    }

    public static Object lookup(String name) throws NamingException {
        Object obj = null;
        Context ctx = null;

        try {
            ctx = getInitialContext();
            obj = ctx.lookup(name);
        } finally {
            close(ctx);
        }

        return obj;
    }

    public static void close(Context ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception ex) {
                //TODO;
            }
        }
    }

}
