BUILD_DIR     = out
MAIN_BUILD    = $(BUILD_DIR)/main
TEST_BUILD    = $(BUILD_DIR)/test

SRC_MAIN      = src/main
SRC_TEST      = src/test

TOOL_DIR      = tools
LIB_DIR       = lib

CS_FLUX_VERSION = 1.0.1
CS_FLUX_JAR     = $(LIB_DIR)/org.x96.sys.foundation.io.jar
CS_FLUX_URL     = https://github.com/x96-sys/flux.java/releases/download/v$(CS_FLUX_VERSION)/org.x96.sys.foundation.io.jar

CS_KIND_VERSION = 0.1.3
CS_KIND_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.token.kind.jar
CS_KIND_URL     = https://github.com/x96-sys/cs.lexer.token.kind.java/releases/download/$(CS_KIND_VERSION)/org.x96.sys.foundation.cs.lexer.token.kind.jar

CS_TOKEN_VERSION = 0.1.3
CS_TOKEN_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.token.jar
CS_TOKEN_URL     = https://github.com/x96-sys/cs.lexer.token.java/releases/download/v$(CS_TOKEN_VERSION)/org.x96.sys.foundation.cs.lexer.token.jar

CS_TOKENIZER_VERSION = 0.1.7
CS_TOKENIZER_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.tokenizer.jar
CS_TOKENIZER_URL     = https://github.com/x96-sys/cs.lexer.tokenizer.java/releases/download/v$(CS_TOKENIZER_VERSION)/org.x96.sys.foundation.cs.lexer.tokenizer.jar

CS_ROUTER_VERSION = 0.1.3
CS_ROUTER_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.router.jar
CS_ROUTER_URL     = https://github.com/x96-sys/cs.lexer.router.java/releases/download/v$(CS_ROUTER_VERSION)/org.x96.sys.foundation.cs.lexer.router.jar

CS_VISITOR_VERSION = 0.1.6
CS_VISITOR_JAR = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.visitor.jar
CS_VISITOR_URL = https://github.com/x96-sys/cs.lexer.visitor.java/releases/download/v$(CS_VISITOR_VERSION)/org.x96.sys.foundation.cs.lexer.visitor.jar

CS_TERMINALS_VERSION = 0.1.3
CS_TERMINALS_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.entry.jar
CS_TERMINALS_URL     = https://github.com/x96-sys/cs.lexer.visitor.entry.java/releases/download/v$(CS_TERMINALS_VERSION)/org.x96.sys.foundation.cs.lexer.entry.jar

CS_KIND_VERSION = 0.1.3
CS_KIND_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.token.kind.jar
CS_KIND_URL     = https://github.com/x96-sys/cs.lexer.token.kind.java/releases/download/$(CS_KIND_VERSION)/org.x96.sys.foundation.cs.lexer.token.kind.jar

CS_IR_VERSION = 0.1.2
CS_IR_JAR = $(LIB_DIR)/org.x96.sys.foundation.cs.ir.jar
CS_IR_URL = https://github.com/x96-sys/cs.ir.java/releases/download/v$(CS_IR_VERSION)/org.x96.sys.foundation.cs.ir.jar

JUNIT_VERSION = 1.13.4
JUNIT_JAR     = $(TOOL_DIR)/junit-platform-console-standalone.jar
JUNIT_URL     = https://maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$(JUNIT_VERSION)/junit-platform-console-standalone-$(JUNIT_VERSION).jar

JACOCO_VERSION = 0.8.13
JACOCO_BASE    = https://maven.org/maven2/org/jacoco

JACOCO_CLI_VERSION = $(JACOCO_VERSION)
JACOCO_CLI_JAR     = $(TOOL_DIR)/jacococli.jar
JACOCO_CLI_URL     = $(JACOCO_BASE)/org.jacoco.cli/$(JACOCO_CLI_VERSION)/org.jacoco.cli-$(JACOCO_CLI_VERSION)-nodeps.jar

JACOCO_AGENT_VERSION = $(JACOCO_VERSION)
JACOCO_AGENT_JAR     = $(TOOL_DIR)/jacocoagent-runtime.jar
JACOCO_AGENT_URL     = $(JACOCO_BASE)/org.jacoco.agent/$(JACOCO_AGENT_VERSION)/org.jacoco.agent-$(JACOCO_AGENT_VERSION)-runtime.jar

GJF_VERSION = 1.28.0
GJF_JAR     = $(TOOL_DIR)/google-java-format.jar
GJF_URL     = https://maven.org/maven2/com/google/googlejavaformat/google-java-format/$(GJF_VERSION)/google-java-format-$(GJF_VERSION)-all-deps.jar

JAVA_SOURCES := $(shell find $(SRC_MAIN) -name "*.java")

DISTRO_JAR = org.x96.sys.foundation.cs.emit.jar

CP = $(CS_IR_JAR):$(CS_TERMINALS_JAR):$(CS_FLUX_JAR):$(CS_KIND_JAR):$(CS_TOKEN_JAR):$(CS_TOKENIZER_JAR):$(CS_ROUTER_JAR):$(CS_VISITOR):$(CS_EMIT_JAR)

build: libs clean/build/main
	@echo "[🦾] Building main sources..."
	@javac -d $(MAIN_BUILD) -cp $(CP) $(JAVA_SOURCES)
	@echo "[💽] Main sources built successfully."

build/test: kit build clean/build/test
	@echo "[🧪] Building test sources..."
	@javac -cp $(JUNIT_JAR):$(MAIN_BUILD):$(CP) -d $(TEST_BUILD) \
	   $(shell find $(SRC_TEST) -name "*.java")
	@echo "[✅] Test sources built successfully."

test: build/test
	@java -jar $(JUNIT_JAR) \
	   execute \
	   --class-path $(TEST_BUILD):$(MAIN_BUILD):$(CLI_BUILD):$(CP) \
	   --scan-class-path

COVERAGE_EXEC = $(BUILD_DIR)/jacoco.exec
COVERAGE_REPORT = $(BUILD_DIR)/coverage

coverage: build/test $(COVERAGE_REPORT)
	@echo "[📊] Running tests with JaCoCo agent..."
	@java -javaagent:$(JACOCO_AGENT_JAR)=destfile=$(COVERAGE_EXEC) \
		-jar $(JUNIT_JAR) \
		execute \
		--class-path $(TEST_BUILD):$(MAIN_BUILD):$(CP) \
		--scan-class-path
	@echo "[📑] Generating coverage report..."
	@java -jar $(JACOCO_CLI_JAR) report $(COVERAGE_EXEC) \
		--classfiles $(MAIN_BUILD) \
		--sourcefiles $(SRC_MAIN) \
		--html $(COVERAGE_REPORT) \
		--xml  $(COVERAGE_REPORT)/coverage.xml \
		--csv  $(COVERAGE_REPORT)/coverage.csv
	@echo "[✅] Coverage report available in $(COVERAGE_REPORT)/index.html"

define deps
$1/$2: $1
	@if [ ! -f "$$($3_JAR)" ]; then \
		echo "[📦] [🚛] [$$($3_VERSION)] [$2]"; \
		curl -sSL -o $$($3_JAR) $$($3_URL); \
	else \
		echo "[📦] [📍] [$$($3_VERSION)] [$2]"; \
	fi
endef

libs: \
	$(LIB_DIR)/flux \
	$(LIB_DIR)/cs-token \
	$(LIB_DIR)/cs-tokenizer \
	$(LIB_DIR)/cs-kind \
	$(LIB_DIR)/cs-router \
	$(LIB_DIR)/cs-terminals \
	$(LIB_DIR)/cs-ir

$(eval $(call deps,$(LIB_DIR),flux,CS_FLUX))
$(eval $(call deps,$(LIB_DIR),cs-ir,CS_IR))
$(eval $(call deps,$(LIB_DIR),cs-token,CS_TOKEN))
$(eval $(call deps,$(LIB_DIR),cs-tokenizer,CS_TOKENIZER))
$(eval $(call deps,$(LIB_DIR),cs-kind,CS_KIND))
$(eval $(call deps,$(LIB_DIR),cs-router,CS_ROUTER))
$(eval $(call deps,$(LIB_DIR),cs-terminals,CS_TERMINALS))

kit: \
	$(TOOL_DIR)/gjf \
	$(TOOL_DIR)/junit \
	$(TOOL_DIR)/jacoco_cli \
	$(TOOL_DIR)/jacoco_agent

$(eval $(call deps,$(TOOL_DIR),gjf,GJF))
$(eval $(call deps,$(TOOL_DIR),junit,JUNIT))
$(eval $(call deps,$(TOOL_DIR),jacoco_cli,JACOCO_CLI))
$(eval $(call deps,$(TOOL_DIR),jacoco_agent,JACOCO_AGENT))

$(TOOL_DIR) $(LIB_DIR) $(COVERAGE_REPORT):
	@mkdir -p $@

distro:
	jar cf $(DISTRO_JAR) -C $(MAIN_BUILD) .

clean/build:
	@rm -rf $(BUILD_DIR)

clean/build/main:
	@rm -rf $(MAIN_BUILD)

clean/build/test:
	@rm -rf $(TEST_BUILD)

clean/lib:
	@rm -rf $(LIB_DIR)

clean/tools:
	@rm -rf $(TOOL_DIR)

clean: \
	clean/build \
	clean/lib \
	clean/tools
