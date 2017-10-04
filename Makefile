# Makefile author: Sagun Pandey <contact@sagunpandey.com>

# Java compiler
JAVAC = javac
JVM = 1.8

# Directory for compiled binaries
BIN = ./bin/

# Directory of source files
SRC = ./src/

FLAGS = -g -d $(BIN) -cp $(SRC) -target $(JVM)

COMPILE = $(JAVAC) $(FLAGS)

JAVA_FILES = $(wildcard $(SRC)*.java)

CLASS_FILES = $(JAVA_FILES:.java=.class)

all: clean $(addprefix $(BIN), $(notdir $(CLASS_FILES)))

$(BIN)%.class: $(SRC)%.java
	@mkdir -p $(BIN)
	$(COMPILE) $<

clean:
	rm -rf $(BIN)*