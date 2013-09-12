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
#       Port to Ruby by Francois Perrad, francois.perrad@gadz.org
#
# RCS ID
# $Id: Makefile,v 1.11 2008/07/29 06:51:08 fperrad Exp $
#
# CHANGE LOG
# (See the bottom of this file.)
#

#################################################################
# Macros.
#

SM_SOURCE_ROOT ?= ../../SmcFiles
SM_SOURCE_DIR ?= $(SM_SOURCE_ROOT)/EX1

SM_SOURCES=     $(SM_SOURCE_DIR)/AppClass.sm
SOURCES=        $(notdir $(SM_SOURCES:%.sm=%_sm.rb))
CHECKSTRING=    ruby -I.. -I. checkstring.rb

SMC_FLAGS+=     -ruby

include ../../Common.mk

all :           checkstring

checkstring :   $(SOURCES)

test: all
		-$(CHECKSTRING) ""
		-$(CHECKSTRING) 000
		-$(CHECKSTRING) 00011
		-$(CHECKSTRING) 111
		-$(CHECKSTRING) 000111100 || true
		-$(CHECKSTRING) 00011a1b10c0 || true


#
# CHANGE LOG
# $Log: Makefile,v $
# Revision 1.11  2008/07/29 06:51:08  fperrad
# - add a target 'png'
#
# Revision 1.10  2008/05/10 12:30:14  fperrad
# - forgotten target test'
#
# Revision 1.9  2008/04/24 09:29:55  fperrad
# + fix #1934494 : remove 'test' from 'all' target
#
# Revision 1.8  2008/02/04 11:06:07  fperrad
# + Exhibit options
#
# Revision 1.7  2008/01/24 07:50:23  fperrad
# - add a target 'table'
#
# Revision 1.6  2008/01/22 08:47:03  fperrad
# - add a target 'graph'
#
# Revision 1.5  2007/10/12 08:55:03  fperrad
# - refactor: Makefile with variables
#
# Revision 1.4  2007/01/15 16:58:57  cwrapp
# Release 4.4.0 second commit.
#
# Revision 1.3  2007/01/03 14:43:23  fperrad
# + Added -reflect option for Perl, Python and Ruby code generation
#
# Revision 1.2  2006/09/17 16:26:06  cwrapp
# Initial v. 4.3.3 check-in.
#
# Revision 1.1  2005/06/16 17:52:03  fperrad
# Added Ruby examples 1 - 4 and 7.
#
