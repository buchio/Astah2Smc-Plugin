#
# The contents of this file are subject to the Mozilla Public
# License Version 1.1 (the "License"); you may not use this file
# except in compliance with the License. You may obtain a copy
# of the License at http://www.mozilla.org/MPL/
#
# Software distributed under the License is distributed on an
# "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
# implied. See the License for the specific language governing
# rights and limitations under the License.
#
# The Original Code is State Machine Compiler (SMC).
#
# The Initial Developer of the Original Code is Charles W. Rapp.
# Portions created by Charles W. Rapp are
# Copyright (C) 2000 - 2007. Charles W. Rapp.
# All Rights Reserved.
#
# Contributor(s):
#
# RCS ID
# $Id: Makefile,v 1.9 2008/07/29 06:50:57 fperrad Exp $
#
# CHANGE LOG
# $Log: Makefile,v $
# Revision 1.9  2008/07/29 06:50:57  fperrad
# - add a target 'png'
#
# Revision 1.8  2008/04/23 16:20:25  fperrad
# + fix #1934474 : wrong include path in C++ examples
#
# Revision 1.7  2008/02/04 11:12:16  fperrad
# + Exhibit options
# + add a target 'table'
# + add a target 'graph'
#
# Revision 1.6  2007/12/28 12:34:40  cwrapp
# Version 5.0.1 check-in.
#
# Revision 1.5  2005/05/28 13:31:18  cwrapp
# Updated C++ examples.
#
# Revision 1.0  2003/12/14 19:39:20  charlesr
# Initial revision
#

#################################################################
# Macros.
#

SM_SOURCE_ROOT ?= ../../SmcFiles
SM_SOURCE_DIR ?= $(SM_SOURCE_ROOT)/EX6

SM_SOURCES=     $(SM_SOURCE_DIR)/TcpConnection.sm
COMMON_SOURCES= $(notdir $(SM_SOURCES:.sm=_sm.cpp)) \
                Eventloop.cpp \
                TcpSegment.cpp \
                TcpConnection.cpp \
                TcpClient.cpp \
                TcpServer.cpp
SERVER_SOURCES= AppServer.cpp \
                AppClient.cpp \
                server.cpp
CLIENT_SOURCES= AppClient.cpp \
                AppServer.cpp \
                client.cpp
COMMON_OBJECTS= $(COMMON_SOURCES:.cpp=.o)
SERVER_OBJECTS= $(SERVER_SOURCES:.cpp=.o)
CLIENT_OBJECTS= $(CLIENT_SOURCES:.cpp=.o)

include ../C++Common.mk

all :           server client

common :        $(COMMON_OBJECTS)

server :        common $(SERVER_OBJECTS)
		$(CXX) -g -o $@ $(COMMON_OBJECTS) $(SERVER_OBJECTS)

client :        common $(CLIENT_OBJECTS)
		$(CXX) -g -o $@ $(COMMON_OBJECTS) $(CLIENT_OBJECTS)

