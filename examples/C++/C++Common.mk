# Uncomment to turn off IOStreams for debug.
# NO_STREAMS=     -nostreams

# Uncomment to turn on debug message generation.
TRACE=          -g $(NO_STREAMS)

# Uncomment to turn on serialization.
# SERIAL=         -serial

# Uncomment to turn off try/catch/rethrow generation.
# NO_CATCH=       -nocatch

# Uncomment to turn off exception throws.
# NO_EXCEPT=      -noex

SMC_FLAGS=      -c++ $(TRACE) $(SERIAL) $(NO_CATCH) $(NO_EXCEPT)

include ../../Common.mk

$(TARGET) :     $(HEADERS) $(SOURCES)
		$(CXX) $(CPPFLAGS) -o $@ $(SOURCES)

