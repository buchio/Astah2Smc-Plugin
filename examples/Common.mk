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
# Modified by Yukio.Obuchi.
# Copyright (C) 2013 Charles W. Rapp.
# All Rights Reserved.
#

#################################################################
# Macros.
#

# Uncomment to turn on debug message generation.
TRACE=          -g

SMC=            java -jar ../../Smc.jar
SMC_FLAGS+=     -d . $(TRACE)

CC=             gcc
CFLAGS=         -g -I../include

CXX=            c++
CPPFLAGS=       -g -Wall -Wextra -I../include

RM_F=           rm -f

#################################################################
# Rules.
#

%_sm.h %_sm.c : $(SM_SOURCE_DIR)/%.sm
		$(SMC) $(SMC_FLAGS) $<

%_sm.h %_sm.cpp : $(SM_SOURCE_DIR)/%.sm
		$(SMC) $(SMC_FLAGS) $<

%_sm.rb :       $(SM_SOURCE_DIR)/%.sm
		$(SMC) $(SMC_FLAGS) $<

%_sm.dot :      $(SM_SOURCE_DIR)/%.sm
		$(SMC) -d . -graph -glevel 1 $<

%_sm.png :      %_sm.dot
		dot -T png -o $@ $<

%_sm.html :     $(SM_SOURCE_DIR)/%.sm
		$(SMC) -d . -table $<

all :           $(TARGET)

graph :         $(notdir $(SM_SOURCES:%.sm=%_sm.dot))

png :           $(notdir $(SM_SOURCES:%.sm=%_sm.png))

table :         $(notdir $(SM_SOURCES:%.sm=%_sm.html))

clean :
		-$(RM_F) $(TARGET)
		-$(RM_F) $(OBJECTS)
		-$(RM_F) *_sm.* *.exe
		-$(RM_F) -r *.dSYM

