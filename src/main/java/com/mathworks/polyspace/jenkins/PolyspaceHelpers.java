// Copyright (c) 2019-2024 The MathWorks, Inc.
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

package com.mathworks.polyspace.jenkins;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.mathworks.polyspace.jenkins.utils.PolyspaceHelpersUtils;

public class PolyspaceHelpers {

  private final PolyspaceHelpersUtils utils;

  public PolyspaceHelpers(PolyspaceHelpersUtils utils) {
    this.utils = utils;
  }

  public void reportFilter(final String[] arg) throws IOException, RuntimeException {
    if (arg.length < 5) {
      System.out.println("Usage: ps_helper -report-filter <original_report> <filtered_report> [<owner>] [<title> <value>]+");
      return;
    }

    int n = 0;
    n++;  // the command - no need to be kept
    final Path originalReport = Paths.get(arg[n++]);   // name of the original report
    final Path filteredReport = Paths.get(arg[n++]);   // name of the filtered report

    String owner;
    if ((arg.length % 2) == 0) {
      // even number of arguments => there is owner that is specified
      owner = arg[n++];
    } else {
      owner = "";
    }

    final String[] filters = Arrays.copyOfRange(arg, n, arg.length);

    this.utils.reportFilter(originalReport, filteredReport, owner, filters);
  }

  public void printRunId(final String[] arg) throws IOException, RuntimeException {
    if (arg.length != 2) {
      System.out.println("Usage: ps_helper -print-runid <access upload output>");
      return;
    }
    System.out.println(this.utils.getAccessResultRunId(Paths.get(arg[1])));
  }

  public void printProjectId(final String[] arg) throws IOException, RuntimeException {
    if (arg.length != 2) {
      System.out.println("Usage: ps_helper -print-projectid <access upload output>");
      return;
    }
    System.out.println(this.utils.getAccessResultProjectId(Paths.get(arg[1])));
  }

  public void printProjectUrl(final String[] arg) throws IOException, RuntimeException {
    if (arg.length != 3) {
      System.out.println("Usage: ps_helper -print-projecturl <access upload output> <access_url>");
      return;
    }
    System.out.println(this.utils.getAccessResultUrl(Paths.get(arg[1]), arg[2]));
  }

  public void reportStatus(final String[] arg) throws IOException, NumberFormatException {
    if (arg.length != 3) {
      System.out.println("Usage: ps_helper -report-status <report> <nb_to_fail>");
      return;
    }
    System.out.println(this.utils.getReportStatus(Paths.get(arg[1]), Long.parseLong(arg[2])));
  }

  public void reportCountFindings(final String[] arg) throws IOException, NumberFormatException {
    if (arg.length != 2) {
      System.out.println("Usage: ps_helper -report-count-findings <report>");
      return;
    }
    System.out.println(this.utils.getCountFindings(Paths.get(arg[1])));
  }

  public static void main (String[] arg) throws IOException, RuntimeException, NumberFormatException {
    PolyspaceHelpersUtils utils = new PolyspaceHelpersUtils();
    PolyspaceHelpers helper = new PolyspaceHelpers(utils);

    boolean usage = false;
    if (arg.length == 0) {
        usage = true;
    } else if (arg[0].equals("-report-filter") || arg[0].equals("report_filter")) {
        helper.reportFilter(arg);
    } else if (arg[0].equals("-report-status") || arg[0].equals("report_status")) {
        helper.reportStatus(arg);
    } else if (arg[0].equals("-report-count-findings") || arg[0].equals("report_count_findings")) {
        helper.reportCountFindings(arg);
    } else if (arg[0].equals("-print-runid") || arg[0].equals("print_runid")) {
        helper.printRunId(arg);
    } else if (arg[0].equals("-print-projectid") || arg[0].equals("print_projectid")) {
        helper.printProjectId(arg);
    } else if (arg[0].equals("-print-projecturl") || arg[0].equals("print_projecturl")) {
        helper.printProjectUrl(arg);
    } else {
        usage = true;
    }

    if (usage) {
        String[] empty = {} ;
        helper.reportFilter(empty);
        helper.reportStatus(empty);
        helper.reportCountFindings(empty);
        helper.printRunId(empty);
        helper.printProjectId(empty);
        helper.printProjectUrl(empty);
    }
  }
}
