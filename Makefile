BUILD_DIR     = out
MAIN_BUILD    = $(BUILD_DIR)/main
TEST_BUILD    = $(BUILD_DIR)/test

SRC_MAIN      = src/main
SRC_TEST      = src/test

TOOL_DIR      = tools
LIB_DIR       = lib

IO_VERSION = 1.1.0
IO_BIN     = $(LIB_DIR)/org.x96.sys.io.jar
IO_URL     = https://github.com/x96-sys/io.java/releases/download/v$(IO_VERSION)/org.x96.sys.io.jar
IO_SHA256  = e18d2fdb894386bd24bb08f178e4a6566d7feadaaf8e96d32bd6d9c5dc63c474

KIND_VERSION = 1.0.0
KIND_BIN     = $(LIB_DIR)/org.x96.sys.lexer.token.kind.jar
KIND_URL     = https://github.com/x96-sys/lexer.token.kind.java/releases/download/v$(KIND_VERSION)/org.x96.sys.lexer.token.kind.jar
KIND_SHA256  = 55d12618cd548099d138cbc1e7beda2b78e6a09382ec725523e82f7eb5a31c69

TOKEN_VERSION = 1.0.0
TOKEN_BIN     = $(LIB_DIR)/org.x96.sys.lexer.token.jar
TOKEN_URL     = https://github.com/x96-sys/cs.lexer.token.java/releases/download/v$(TOKEN_VERSION)/org.x96.sys.lexer.token.jar
TOKEN_SHA256  = b58fa314148954ec78d3ead11a434da2670d6d64837807087d2b541190fcf40d

TOKENIZER_VERSION = 1.0.0
TOKENIZER_BIN     = $(LIB_DIR)/org.x96.sys.lexer.tokenizer.jar
TOKENIZER_URL     = https://github.com/x96-sys/lexer.tokenizer.java/releases/download/v$(TOKENIZER_VERSION)/org.x96.sys.lexer.tokenizer.jar
TOKENIZER_SHA256  = 21a10167ffd798f1fa9cbbda1382650a411c826b957bf3cc607863696bf4e8f7

ROUTER_VERSION = 1.0.0
ROUTER_BIN     = $(LIB_DIR)/org.x96.sys.lexer.router.jar
ROUTER_URL     = https://github.com/x96-sys/lexer.router.java/releases/download/v$(ROUTER_VERSION)/org.x96.sys.lexer.router.jar
ROUTER_SHA256  = 575aab309a0f75e0ca9f9a6523c07bafcfeb2314dfa5c5482326c832d4bf63c6

VISITOR_VERSION = 1.0.0
VISITOR_BIN     = $(LIB_DIR)/org.x96.sys.lexer.visitor.jar
VISITOR_URL     = https://github.com/x96-sys/lexer.visitor.java/releases/download/v$(VISITOR_VERSION)/org.x96.sys.lexer.visitor.jar
VISITOR_SHA256  = 2ae4d8669d15c965e30053a7d92a362ea1136c3ef3c3bacdcb9dbbc347bc977e

ENTRY_VERSION = 1.0.0
ENTRY_BIN     = $(LIB_DIR)/org.x96.sys.lexer.entry.jar
ENTRY_URL     = https://github.com/x96-sys/lexer.visitor.entry.java/releases/download/v$(ENTRY_VERSION)/org.x96.sys.lexer.entry.jar
ENTRY_SHA256  = e706396e6d3fdbd69d529a0d5cbd4597699bd7f7d85f563983ee87e4d4fa90b4

CS_IR_VERSION = 1.0.0
CS_IR_BIN     = $(LIB_DIR)/org.x96.sys.cs.ir.jar
CS_IR_URL     = https://github.com/x96-sys/cs.ir.java/releases/download/v$(CS_IR_VERSION)/org.x96.sys.cs.ir.jar
CS_IR_SHA256  = 7a16e2bb1e64ac3465ec270d0a583ae9c069faf1e457d6fef98b7f5154623d21

JUNIT_VERSION = 1.13.4
JUNIT_BIN     = $(TOOL_DIR)/junit-platform-console-standalone.jar
JUNIT_URL     = https://maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$(JUNIT_VERSION)/junit-platform-console-standalone-$(JUNIT_VERSION).jar
JUNIT_SHA256  = 3fdfc37e29744a9a67dd5365e81467e26fbde0b7aa204e6f8bbe79eeaa7ae892

JACOCO_VERSION = 0.8.13
JACOCO_BASE    = https://maven.org/maven2/org/jacoco

JACOCO_CLI_VERSION = $(JACOCO_VERSION)
JACOCO_CLI_BIN     = $(TOOL_DIR)/jacococli.jar
JACOCO_CLI_URL     = $(JACOCO_BASE)/org.jacoco.cli/$(JACOCO_CLI_VERSION)/org.jacoco.cli-$(JACOCO_CLI_VERSION)-nodeps.jar
JACOCO_CLI_SHA256  = 8f748683833d4dc4d72cea5d6b43f49344687b831e0582c97bcb9b984e3de0a3

JACOCO_AGENT_VERSION = $(JACOCO_VERSION)
JACOCO_AGENT_BIN     = $(TOOL_DIR)/jacocoagent-runtime.jar
JACOCO_AGENT_URL     = $(JACOCO_BASE)/org.jacoco.agent/$(JACOCO_AGENT_VERSION)/org.jacoco.agent-$(JACOCO_AGENT_VERSION)-runtime.jar
JACOCO_AGENT_SHA256  = 47e700ccb0fdb9e27c5241353f8161938f4e53c3561dd35e063c5fe88dc3349b

GJF_VERSION = 1.28.0
GJF_BIN     = $(TOOL_DIR)/google-java-format.jar
GJF_URL     = https://maven.org/maven2/com/google/googlejavaformat/google-java-format/$(GJF_VERSION)/google-java-format-$(GJF_VERSION)-all-deps.jar
GJF_SHA256  = 32342e7c1b4600f80df3471da46aee8012d3e1445d5ea1be1fb71289b07cc735

JAVA_SOURCES := $(shell find $(SRC_MAIN) -name "*.java")
JAVA_TEST_SOURCES := $(shell find $(SRC_TEST) -name "*.java")

DISTRO_BIN = org.x96.sys.cs.emit.jar

CP = $(IO_BIN):$(KIND_BIN):$(TOKEN_BIN):$(TOKENIZER_BIN):$(ROUTER_BIN):$(VISITOR_BIN):$(ENTRY_BIN):$(CS_IR_BIN)

build: libs clean/build/main
	@echo "[🦾] Building main sources..."
	@javac -d $(MAIN_BUILD) -cp $(CP) $(JAVA_SOURCES)
	@echo "[💽] Main sources built successfully."

build/test: kit build clean/build/test
	@echo "[🧪] Building test sources..."
	@javac -cp $(JUNIT_BIN):$(MAIN_BUILD):$(CP) -d $(TEST_BUILD) $(JAVA_TEST_SOURCES)
	@echo "[✅] Test sources built successfully."

test: build/test
	@java -jar $(JUNIT_BIN) \
	   execute \
	   --class-path $(TEST_BUILD):$(MAIN_BUILD):$(CLI_BUILD):$(CP) \
	   --scan-class-path

COVERAGE_EXEC = $(BUILD_DIR)/jacoco.exec
COVERAGE_REPORT = $(BUILD_DIR)/coverage

coverage: build/test $(COVERAGE_REPORT)
	@echo "[📊] Running tests with JaCoCo agent..."
	@java -javaagent:$(JACOCO_AGENT_BIN)=destfile=$(COVERAGE_EXEC) \
		-jar $(JUNIT_BIN) \
		execute \
		--class-path $(TEST_BUILD):$(MAIN_BUILD):$(CP) \
		--scan-class-path
	@echo "[📑] Generating coverage report..."
	@java -jar $(JACOCO_CLI_BIN) report $(COVERAGE_EXEC) \
		--classfiles $(MAIN_BUILD) \
		--sourcefiles $(SRC_MAIN) \
		--html $(COVERAGE_REPORT) \
		--xml  $(COVERAGE_REPORT)/coverage.xml \
		--csv  $(COVERAGE_REPORT)/coverage.csv
	@echo "[✅] Coverage report available in $(COVERAGE_REPORT)/index.html"

define deps
$1/$2: $1
	@expected="$($3_SHA256)"; \
	bin="$($3_BIN)"; \
	url="$($3_URL)"; \
	tmp="$$$$(mktemp)"; \
	if [ ! -f "$$$$bin" ]; then \
		echo "[📦] [🚛] [$($3_VERSION)] [$2]"; \
		curl -sSL -o "$$$$tmp" "$$$$url"; \
		actual="$$$$(shasum -a 256 $$$$tmp | awk '{print $$$$1}')"; \
		if [ "$$$$expected" = "$$$$actual" ]; then mv "$$$$tmp" "$$$$bin"; \
		echo "[📦] [📍] [$($3_VERSION)] [$2] [🐚]"; else rm "$$$$tmp"; \
		echo "[❌] [hash mismatch] [$2]"; exit 1; fi; \
	else \
		actual="$$$$(shasum -a 256 $$$$bin | awk '{print $$$$1}')"; \
		if [ "$$$$expected" = "$$$$actual" ]; \
		then echo "[📦] [📍] [$($3_VERSION)] [🐚] [$2]"; \
		else \
			echo "[❌] [hash mismatch] [$2]"; \
			curl -sSL -o "$$$$tmp" "$$$$url"; \
			actual="$$$$(shasum -a 256 $$$$tmp | awk '{print $$$$1}')"; \
			if [ "$$$$expected" = "$$$$actual" ]; then mv "$$$$tmp" "$$$$bin"; \
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
$(eval $(call deps,$(LIB_DIR),ir,CS_IR))

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
	jar cf $(DISTRO_BIN) -C $(MAIN_BUILD) .

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
