BUILD_DIR     = out
MAIN_BUILD    = $(BUILD_DIR)/main
TEST_BUILD    = $(BUILD_DIR)/test

SRC_MAIN      = src/main
SRC_TEST      = src/test

TOOL_DIR      = tools
LIB_DIR       = lib

IO_VERSION = 1.0.2
IO_JAR     = $(LIB_DIR)/org.x96.sys.foundation.io.jar
IO_URL     = https://github.com/x96-sys/io.java/releases/download/v$(IO_VERSION)/org.x96.sys.foundation.io.jar
IO_SHA256  = b1d4744f813dfb7a00237de28d5b3b2f5c831d470f8a34901a0c9b4057fd486b

KIND_VERSION = 0.1.3
KIND_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.token.kind.jar
KIND_URL     = https://github.com/x96-sys/cs.lexer.token.kind.java/releases/download/$(KIND_VERSION)/org.x96.sys.foundation.cs.lexer.token.kind.jar
KIND_SHA256  = 269317df6833c28202d9a695a91a89340c0027d10874d57770f2626a8eb7f5fe

TOKEN_VERSION = 0.1.3
TOKEN_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.token.jar
TOKEN_URL     = https://github.com/x96-sys/cs.lexer.token.java/releases/download/v$(TOKEN_VERSION)/org.x96.sys.foundation.cs.lexer.token.jar
TOKEN_SHA256  = 7d25aa60fc975b3830bdd07d12dc4717747e03c9e2a94684d110c3238d540752

TOKENIZER_VERSION = 0.1.7
TOKENIZER_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.tokenizer.jar
TOKENIZER_URL     = https://github.com/x96-sys/cs.lexer.tokenizer.java/releases/download/v$(TOKENIZER_VERSION)/org.x96.sys.foundation.cs.lexer.tokenizer.jar
TOKENIZER_SHA256  = 9f32f2e879c06dc7479ea1509bedb794b3bdb189e2c80284d8fd18b95f8ab2df

ROUTER_VERSION = 0.1.4
ROUTER_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.router.jar
ROUTER_URL     = https://github.com/x96-sys/cs.lexer.router.java/releases/download/v$(ROUTER_VERSION)/org.x96.sys.foundation.cs.lexer.router.jar
ROUTER_SHA256  = 1a5e0736a4928a509843daf9edb936b730e7264cd23836fb3a750a83e3888e9a

VISITOR_VERSION = 0.1.6
VISITOR_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.visitor.jar
VISITOR_URL     = https://github.com/x96-sys/cs.lexer.visitor.java/releases/download/v$(VISITOR_VERSION)/org.x96.sys.foundation.cs.lexer.visitor.jar
VISITOR_SHA256  = 309c5f1eb169dacb184a95a57cb8d513f5daabbcdaa3ba092f04f332236a7467

ENTRY_VERSION = 0.1.3
ENTRY_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.lexer.entry.jar
ENTRY_URL     = https://github.com/x96-sys/cs.lexer.visitor.entry.java/releases/download/v$(ENTRY_VERSION)/org.x96.sys.foundation.cs.lexer.entry.jar
ENTRY_SHA256  = c4b88c7836d8123714ca2d1cc373b43439db19fa9052b6159d987e6622b461bb

IR_VERSION = 0.1.2
IR_JAR     = $(LIB_DIR)/org.x96.sys.foundation.cs.ir.jar
IR_URL     = https://github.com/x96-sys/cs.ir.java/releases/download/v$(IR_VERSION)/org.x96.sys.foundation.cs.ir.jar
IR_SHA256  = 14f6af5e59c491a3e7ca92b3753ba28015a1f52e185e3cca768b8af403fe0d41

JUNIT_VERSION = 1.13.4
JUNIT_JAR     = $(TOOL_DIR)/junit-platform-console-standalone.jar
JUNIT_URL     = https://maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$(JUNIT_VERSION)/junit-platform-console-standalone-$(JUNIT_VERSION).jar
JUNIT_SHA256  = 3fdfc37e29744a9a67dd5365e81467e26fbde0b7aa204e6f8bbe79eeaa7ae892

JACOCO_VERSION = 0.8.13
JACOCO_BASE    = https://maven.org/maven2/org/jacoco

JACOCO_CLI_VERSION = $(JACOCO_VERSION)
JACOCO_CLI_JAR     = $(TOOL_DIR)/jacococli.jar
JACOCO_CLI_URL     = $(JACOCO_BASE)/org.jacoco.cli/$(JACOCO_CLI_VERSION)/org.jacoco.cli-$(JACOCO_CLI_VERSION)-nodeps.jar
JACOCO_CLI_SHA256  = 8f748683833d4dc4d72cea5d6b43f49344687b831e0582c97bcb9b984e3de0a3

JACOCO_AGENT_VERSION = $(JACOCO_VERSION)
JACOCO_AGENT_JAR     = $(TOOL_DIR)/jacocoagent-runtime.jar
JACOCO_AGENT_URL     = $(JACOCO_BASE)/org.jacoco.agent/$(JACOCO_AGENT_VERSION)/org.jacoco.agent-$(JACOCO_AGENT_VERSION)-runtime.jar
JACOCO_AGENT_SHA256  = 47e700ccb0fdb9e27c5241353f8161938f4e53c3561dd35e063c5fe88dc3349b

GJF_VERSION = 1.28.0
GJF_JAR     = $(TOOL_DIR)/google-java-format.jar
GJF_URL     = https://maven.org/maven2/com/google/googlejavaformat/google-java-format/$(GJF_VERSION)/google-java-format-$(GJF_VERSION)-all-deps.jar
GJF_SHA256  = 32342e7c1b4600f80df3471da46aee8012d3e1445d5ea1be1fb71289b07cc735

JAVA_SOURCES := $(shell find $(SRC_MAIN) -name "*.java")
JAVA_TEST_SOURCES := $(shell find $(SRC_TEST) -name "*.java")

DISTRO_JAR = org.x96.sys.foundation.cs.emit.jar

CP = $(IO_JAR):$(KIND_JAR):$(TOKEN_JAR):$(TOKENIZER_JAR):$(ROUTER_JAR):$(VISITOR_JAR):$(ENTRY_JAR):$(IR_JAR)

build: libs clean/build/main
	@echo "[🦾] Building main sources..."
	@javac -d $(MAIN_BUILD) -cp $(CP) $(JAVA_SOURCES)
	@echo "[💽] Main sources built successfully."

build/test: kit build clean/build/test
	@echo "[🧪] Building test sources..."
	@javac -cp $(JUNIT_JAR):$(MAIN_BUILD):$(CP) -d $(TEST_BUILD) $(JAVA_TEST_SOURCES)
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
	@expected="$($3_SHA256)"; \
	jar="$($3_JAR)"; \
	url="$($3_URL)"; \
	tmp="$$$$(mktemp)"; \
	if [ ! -f "$$$$jar" ]; then \
		echo "[📦] [🚛] [$($3_VERSION)] [$2]"; \
		curl -sSL -o "$$$$tmp" "$$$$url"; \
		actual="$$$$(shasum -a 256 $$$$tmp | awk '{print $$$$1}')"; \
		if [ "$$$$expected" = "$$$$actual" ]; then mv "$$$$tmp" "$$$$jar"; \
		echo "[📦] [📍] [$($3_VERSION)] [$2] [🐚]"; else rm "$$$$tmp"; \
		echo "[❌] [hash mismatch] [$2]"; exit 1; fi; \
	else \
		actual="$$$$(shasum -a 256 $$$$jar | awk '{print $$$$1}')"; \
		if [ "$$$$expected" = "$$$$actual" ]; \
		then echo "[📦] [📍] [$($3_VERSION)] [🐚] [$2]"; \
		else \
			echo "[❌] [hash mismatch] [$2]"; \
			curl -sSL -o "$$$$tmp" "$$$$url"; \
			actual="$$$$(shasum -a 256 $$$$tmp | awk '{print $$$$1}')"; \
			if [ "$$$$expected" = "$$$$actual" ]; then mv "$$$$tmp" "$$$$jar"; \
			echo "[📦] [♻️] [$($3_VERSION)] [🐚] [$2]"; else rm "$$$$tmp"; \
			echo "[❌] [download failed] [$2]"; exit 1; fi; \
		fi; \
	fi
endef

$(eval $(call deps,$(LIB_DIR),io,IO))
$(eval $(call deps,$(LIB_DIR),kind,KIND))
$(eval $(call deps,$(LIB_DIR),token,TOKEN))
$(eval $(call deps,$(LIB_DIR),tokenizer,TOKENIZER))
$(eval $(call deps,$(LIB_DIR),router,ROUTER))
$(eval $(call deps,$(LIB_DIR),visitor,VISITOR))
$(eval $(call deps,$(LIB_DIR),entry,ENTRY))
$(eval $(call deps,$(LIB_DIR),ir,IR))

libs: \
	$(LIB_DIR)/io \
	$(LIB_DIR)/kind \
	$(LIB_DIR)/token \
	$(LIB_DIR)/tokenizer \
	$(LIB_DIR)/router \
	$(LIB_DIR)/visitor \
	$(LIB_DIR)/entry \
	$(LIB_DIR)/ir

$(eval $(call deps,$(TOOL_DIR),gjf,GJF))
$(eval $(call deps,$(TOOL_DIR),junit,JUNIT))
$(eval $(call deps,$(TOOL_DIR),jacoco_cli,JACOCO_CLI))
$(eval $(call deps,$(TOOL_DIR),jacoco_agent,JACOCO_AGENT))

kit: \
	$(TOOL_DIR)/gjf \
	$(TOOL_DIR)/junit \
	$(TOOL_DIR)/jacoco_cli \
	$(TOOL_DIR)/jacoco_agent

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
