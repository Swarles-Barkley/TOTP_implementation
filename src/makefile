JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    com/company/TOTP.java \
	com/company/Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) com/company/*.class
