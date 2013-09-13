# Copyright (c) 2013 Yukio Obuchi
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

test:
	cd ../CLI && mvn integration-test
	SM_SOURCE_ROOT=../../output/smc make -C C/EX1 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C C/EX2 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C C/EX3 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C C++/EX1 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C C++/EX2 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C C++/EX3 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C Ruby/EX1 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C Ruby/EX2 clean all test
	SM_SOURCE_ROOT=../../output/smc make -C Ruby/EX3 clean all test

clean:
	rm -rf ../CLI/target ../target output
	make -C C/EX1 clean
	make -C C/EX2 clean
	make -C C/EX3 clean
	make -C C++/EX1 clean
	make -C C++/EX2 clean
	make -C C++/EX3 clean
	make -C Ruby/EX1 clean
	make -C Ruby/EX2 clean
	make -C Ruby/EX3 clean
	git checkout output
