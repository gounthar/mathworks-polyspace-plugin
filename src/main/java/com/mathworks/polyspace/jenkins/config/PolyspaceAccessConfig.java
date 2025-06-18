// Copyright (c) 2019-2023 The MathWorks, Inc.
// All Rights Reserved.
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.mathworks.polyspace.jenkins.config;

import org.kohsuke.stapler.*;

import com.mathworks.polyspace.jenkins.utils.PolyspaceConfigUtils;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import java.io.*;
import javax.servlet.ServletException;


public class PolyspaceAccessConfig extends AbstractDescribableImpl<PolyspaceAccessConfig> {

    private String polyspaceAccessName = null;
    private String polyspaceAccessProtocol = null;
    private String polyspaceAccessHost = null;
    private String polyspaceAccessPort = null;

    @DataBoundConstructor
    public PolyspaceAccessConfig() { }

    @DataBoundSetter
    public void setPolyspaceAccessName(String polyspaceAccessName) {
      this.polyspaceAccessName = polyspaceAccessName;
    }

    @DataBoundSetter
    public void setPolyspaceAccessProtocol(String polyspaceAccessProtocol) {
      this.polyspaceAccessProtocol = polyspaceAccessProtocol;
    }

    @DataBoundSetter
    public void setPolyspaceAccessHost(String polyspaceAccessHost) {
      this.polyspaceAccessHost = polyspaceAccessHost;
    }

    @DataBoundSetter
    public void setPolyspaceAccessPort(String polyspaceAccessPort) {
      this.polyspaceAccessPort = polyspaceAccessPort;
    }

    public String getPolyspaceAccessName() {
        return polyspaceAccessName;
    }
    public String getPolyspaceAccessProtocol() {
        return polyspaceAccessProtocol;
    }
    public String getPolyspaceAccessHost() {
        return polyspaceAccessHost;
    }
    public String getPolyspaceAccessPort() {
        return polyspaceAccessPort;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<PolyspaceAccessConfig> {
        public String getDisplayName() { return Messages.polyspaceAccessConfigDisplayName(); }
        
        public FormValidation doCheckPolyspaceAccessProtocol(@QueryParameter String value)
            throws IOException, ServletException {
              return PolyspaceConfigUtils.doCheckProtocol(value);
        }

        public FormValidation doCheckPolyspaceAccessPort(@QueryParameter String value)
            throws IOException, ServletException {
              return PolyspaceConfigUtils.doCheckPort(value);
        }
    }
}
