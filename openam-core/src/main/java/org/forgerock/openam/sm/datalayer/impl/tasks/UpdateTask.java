/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2014-2016 ForgeRock AS.
 */
package org.forgerock.openam.sm.datalayer.impl.tasks;

import java.text.MessageFormat;

import org.forgerock.openam.cts.api.tokens.Token;
import org.forgerock.openam.sm.datalayer.api.AbstractTask;
import org.forgerock.openam.sm.datalayer.api.DataLayerException;
import org.forgerock.openam.sm.datalayer.api.ResultHandler;
import org.forgerock.openam.sm.datalayer.api.TokenStorageAdapter;

/**
 * Responsible for updating the persistence layer with the provided Token.
 */
public class UpdateTask extends AbstractTask {
    private final Token token;

    /**
     * @param token Non null Token to update.
     * @param handler Non null handler to notify.
     */
    public UpdateTask(Token token, ResultHandler<Token, ?> handler) {
        super(handler);
        this.token = token;
    }

    /**
     * Performs a read of the store to determine the state of the Token.
     *
     * If the Token exists, then an update is performed, otherwise a create is
     * performed.
     *
     * @param adapter Non null for connection-coupled operations.
     * @throws DataLayerException If there was an error of any kind.
     */
    @Override
    public void performTask(TokenStorageAdapter adapter) throws DataLayerException {
        Token previous = adapter.read(token.getTokenId());
        if (previous == null) {
            adapter.create(token);
        } else {
            adapter.update(previous, token);
        }
        handler.processResults(token);
    }

    @Override
    public String toString() {
        return MessageFormat.format("UpdateTask: {0}", token.getTokenId());
    }
}
