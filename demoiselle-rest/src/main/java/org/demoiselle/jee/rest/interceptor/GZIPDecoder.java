/*
 * Demoiselle Framework
 *
 * License: GNU Lesser General Public License (LGPL), version 3 or later.
 * See the lgpl.txt file in the root directory or <https://www.gnu.org/licenses/lgpl.html>.
 */
package org.demoiselle.jee.rest.interceptor;

import java.io.IOException;
import java.util.zip.GZIPInputStream;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import org.demoiselle.jee.rest.DemoiselleRestConfig;

/**
 *
 * @author SERPRO
 */
@Provider
public class GZIPDecoder implements ReaderInterceptor {

    @Inject
    private DemoiselleRestConfig config;

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ctx) throws IOException {

        if (config.isGzipEnabled()) {
            String encoding = ctx.getHeaders().getFirst("Content-Encoding");
            if (!"gzip".equalsIgnoreCase(encoding)) {
                return ctx.proceed();
            }
            GZIPInputStream is = new GZIPInputStream(ctx.getInputStream());
            ctx.setInputStream(is);
        }
        return ctx.proceed();
    }
}
