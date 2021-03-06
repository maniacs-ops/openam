/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005 Sun Microsystems Inc. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * https://opensso.dev.java.net/public/CDDLv1.0.html or
 * opensso/legal/CDDLv1.0.txt
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at opensso/legal/CDDLv1.0.txt.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Portions Copyrighted 2014-2015 ForgeRock AS.
 */

package org.forgerock.openam.session;

import com.iplanet.am.util.SystemProperties;

public class SessionMeta {

    /**
     * This is the maximum extra time for which the timed out sessions can live
     * in the session server (in seconds).
     */
    private static long purgeDelay;

    public static long getPurgeDelay() {
        return purgeDelay;
    }

    /**
     * How long before an appSSOToken must refresh (in minutes).
     */
    private static long appSSOTokenRefreshTime;

    public static long getAppSSOTokenRefreshTime() {
        return appSSOTokenRefreshTime;
    }

    static {
        purgeDelay = SystemProperties.getAsLong("com.iplanet.am.session.purgedelay", 120);
        appSSOTokenRefreshTime = SystemProperties.getAsLong("com.iplanet.am.client.appssotoken.refreshtime", 3);
    }

}
