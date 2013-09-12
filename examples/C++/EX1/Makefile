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
# Copyright (C) 2000 Charles W. Rapp.
# All Rights Reserved.
#
# Contributor(s):
#
# RCS ID
# $Id: Makefile,v 1.12 2008/07/29 06:50:56 fperrad Exp $
#
# CHANGE LOG
# (See the bottom of this file.)
#

#################################################################
# Macros.
#

SM_SOURCE_DIR?= ../../SmcFiles/EX1

SM_SOURCES=     $(SM_SOURCE_DIR)/AppClass.sm

SOURCES=        $(notdir $(SM_SOURCES:.sm=_sm.cpp)) \
                AppClass.cpp \
                Main.cpp
HEADERS=        $(notdir $(SM_SOURCES:.sm=_sm.h)) \
                AppClass.h
OBJECTS=        $(SOURCES:.cpp=.o)
TARGET=         checkstring
CHECKSTRING=    ./$(TARGET)

include ../C++Common.mk

test :          $(TARGET)
		-$(CHECKSTRING) ""
		-$(CHECKSTRING) 000
		-$(CHECKSTRING) 00011
		-$(CHECKSTRING) 111
		-$(CHECKSTRING) 000111100 || true
		-$(CHECKSTRING) 00011a1b10c0 || true

