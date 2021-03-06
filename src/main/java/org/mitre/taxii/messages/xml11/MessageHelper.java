package org.mitre.taxii.messages.xml11;
/*
Copyright (c) 2014, The MITRE Corporation
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of The MITRE Corporation nor the 
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This class provides some convenience methods to make constructing TAXII messages
 * easier.
 *
 * @author jasenj1
 */
public class MessageHelper {
    
    /**
     * Generates a random message id.
     * 
     * @return a random UUID URN complying with RFC 4122 (a type 4 UUID per RFC 4122).
     */
    public static final String generateMessageId() {
        return "urn:uuid:" + UUID.randomUUID().toString();
    }    
    
    /**
     * 
     * @param name
     * @param value
     * @return 
     */
    public static ExtendedHeaderType createExtendedHeader(final URI name, final Object value) {
        final ExtendedHeaderType eht = new ExtendedHeaderType().withName(name.toString());
        eht.getContent().add(value.toString());        
        return eht;
    }
    
    /**
     * 
     * @param m
     * @param name
     * @param value
     * @return 
     */
    public static MessageType addExtendedHeader(final MessageType m, final URI name, final Object... value) {
        
        ExtendedHeadersType eht = m.getExtendedHeaders();
        
        if (null == eht) { // There are no extended headers. Add them.
            final ExtendedHeadersType newEht = new ExtendedHeadersType();
            m.setExtendedHeaders(newEht);
            eht = m.getExtendedHeaders();
        }
        
        final List<ExtendedHeaderType> extendedHeaders = eht.getExtendedHeaders();
        
        for (Object item: value) {
            final ExtendedHeaderType eh = createExtendedHeader(name, item);
            extendedHeaders.add(eh);             
        }
        
        return m;
        
    }
    
    /**
     * Not implemented yet.
     * 
     * @param m
     * @param headerMap
     * @return 
     */
    public static MessageType addExtendedHeaders(final MessageType m, final Map headerMap) {
        return m;
    }
    
}
