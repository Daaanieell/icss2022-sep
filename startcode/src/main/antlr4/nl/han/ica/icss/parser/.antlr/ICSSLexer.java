// Generated from /home/daniel/github-repo/work/cni/app/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, BOX_BRACKET_OPEN=3, BOX_BRACKET_CLOSE=4, TRUE=5, FALSE=6, 
		PIXELSIZE=7, PERCENTAGE=8, SCALAR=9, COLOR=10, ID_IDENT=11, CLASS_IDENT=12, 
		LOWER_IDENT=13, CAPITAL_IDENT=14, WS=15, OPEN_BRACE=16, CLOSE_BRACE=17, 
		SEMICOLON=18, COLON=19, PLUS=20, MIN=21, MUL=22, ASSIGNMENT_OPERATOR=23, 
		DIV=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", "FALSE", 
			"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "DIV"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "';'", "':'", 
			"'+'", "'-'", "'*'", "':='", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", 
			"FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "DIV"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0018\u0098\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0004\u0006J\b\u0006\u000b\u0006\f\u0006K\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0004\u0007R\b\u0007\u000b\u0007\f\u0007S\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0004\bY\b\b\u000b\b\f\bZ\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0004\ng\b\n"+
		"\u000b\n\f\nh\u0001\u000b\u0001\u000b\u0004\u000bm\b\u000b\u000b\u000b"+
		"\f\u000bn\u0001\f\u0001\f\u0005\fs\b\f\n\f\f\fv\t\f\u0001\r\u0001\r\u0005"+
		"\rz\b\r\n\r\f\r}\t\r\u0001\u000e\u0004\u000e\u0080\b\u000e\u000b\u000e"+
		"\f\u000e\u0081\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0000\u0000\u0018\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u0018\u0001\u0000\u0007\u0001\u000009\u0002\u000009af\u0003\u0000--0"+
		"9az\u0001\u0000az\u0001\u0000AZ\u0004\u000009AZ__az\u0003\u0000\t\n\r"+
		"\r  \u009f\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00011\u0001\u0000\u0000\u0000\u0003"+
		"4\u0001\u0000\u0000\u0000\u00059\u0001\u0000\u0000\u0000\u0007;\u0001"+
		"\u0000\u0000\u0000\t=\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000"+
		"\u0000\rI\u0001\u0000\u0000\u0000\u000fQ\u0001\u0000\u0000\u0000\u0011"+
		"X\u0001\u0000\u0000\u0000\u0013\\\u0001\u0000\u0000\u0000\u0015d\u0001"+
		"\u0000\u0000\u0000\u0017j\u0001\u0000\u0000\u0000\u0019p\u0001\u0000\u0000"+
		"\u0000\u001bw\u0001\u0000\u0000\u0000\u001d\u007f\u0001\u0000\u0000\u0000"+
		"\u001f\u0085\u0001\u0000\u0000\u0000!\u0087\u0001\u0000\u0000\u0000#\u0089"+
		"\u0001\u0000\u0000\u0000%\u008b\u0001\u0000\u0000\u0000\'\u008d\u0001"+
		"\u0000\u0000\u0000)\u008f\u0001\u0000\u0000\u0000+\u0091\u0001\u0000\u0000"+
		"\u0000-\u0093\u0001\u0000\u0000\u0000/\u0096\u0001\u0000\u0000\u00001"+
		"2\u0005i\u0000\u000023\u0005f\u0000\u00003\u0002\u0001\u0000\u0000\u0000"+
		"45\u0005e\u0000\u000056\u0005l\u0000\u000067\u0005s\u0000\u000078\u0005"+
		"e\u0000\u00008\u0004\u0001\u0000\u0000\u00009:\u0005[\u0000\u0000:\u0006"+
		"\u0001\u0000\u0000\u0000;<\u0005]\u0000\u0000<\b\u0001\u0000\u0000\u0000"+
		"=>\u0005T\u0000\u0000>?\u0005R\u0000\u0000?@\u0005U\u0000\u0000@A\u0005"+
		"E\u0000\u0000A\n\u0001\u0000\u0000\u0000BC\u0005F\u0000\u0000CD\u0005"+
		"A\u0000\u0000DE\u0005L\u0000\u0000EF\u0005S\u0000\u0000FG\u0005E\u0000"+
		"\u0000G\f\u0001\u0000\u0000\u0000HJ\u0007\u0000\u0000\u0000IH\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KL\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0005p\u0000\u0000NO\u0005"+
		"x\u0000\u0000O\u000e\u0001\u0000\u0000\u0000PR\u0007\u0000\u0000\u0000"+
		"QP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000"+
		"\u0000ST\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UV\u0005%\u0000"+
		"\u0000V\u0010\u0001\u0000\u0000\u0000WY\u0007\u0000\u0000\u0000XW\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000"+
		"Z[\u0001\u0000\u0000\u0000[\u0012\u0001\u0000\u0000\u0000\\]\u0005#\u0000"+
		"\u0000]^\u0007\u0001\u0000\u0000^_\u0007\u0001\u0000\u0000_`\u0007\u0001"+
		"\u0000\u0000`a\u0007\u0001\u0000\u0000ab\u0007\u0001\u0000\u0000bc\u0007"+
		"\u0001\u0000\u0000c\u0014\u0001\u0000\u0000\u0000df\u0005#\u0000\u0000"+
		"eg\u0007\u0002\u0000\u0000fe\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\u0016\u0001"+
		"\u0000\u0000\u0000jl\u0005.\u0000\u0000km\u0007\u0002\u0000\u0000lk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000o\u0018\u0001\u0000\u0000\u0000pt\u0007\u0003"+
		"\u0000\u0000qs\u0007\u0002\u0000\u0000rq\u0001\u0000\u0000\u0000sv\u0001"+
		"\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000"+
		"u\u001a\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000w{\u0007\u0004"+
		"\u0000\u0000xz\u0007\u0005\u0000\u0000yx\u0001\u0000\u0000\u0000z}\u0001"+
		"\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000"+
		"|\u001c\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u0080\u0007"+
		"\u0006\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000"+
		"\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000"+
		"\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0006\u000e"+
		"\u0000\u0000\u0084\u001e\u0001\u0000\u0000\u0000\u0085\u0086\u0005{\u0000"+
		"\u0000\u0086 \u0001\u0000\u0000\u0000\u0087\u0088\u0005}\u0000\u0000\u0088"+
		"\"\u0001\u0000\u0000\u0000\u0089\u008a\u0005;\u0000\u0000\u008a$\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005:\u0000\u0000\u008c&\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0005+\u0000\u0000\u008e(\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005-\u0000\u0000\u0090*\u0001\u0000\u0000\u0000\u0091\u0092\u0005"+
		"*\u0000\u0000\u0092,\u0001\u0000\u0000\u0000\u0093\u0094\u0005:\u0000"+
		"\u0000\u0094\u0095\u0005=\u0000\u0000\u0095.\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0005/\u0000\u0000\u00970\u0001\u0000\u0000\u0000\t\u0000KSZhn"+
		"t{\u0081\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}