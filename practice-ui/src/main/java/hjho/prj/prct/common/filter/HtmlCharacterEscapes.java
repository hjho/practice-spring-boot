package hjho.prj.prct.common.filter;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlCharacterEscapes extends CharacterEscapes {

	private static final long serialVersionUID = -7887438232365715016L;
	
	// 특수문자 지정 변수
	private final int[] asciiEscapes;
	
	// 특수문자 인코딩 값 지정 변수
	private final CharSequenceTranslator translator;
	 
    public HtmlCharacterEscapes() {
    	
    	// 1. XSS 방지 처리할 특수 문자 지정
        this.asciiEscapes       = CharacterEscapes.standardAsciiEscapesForJSON();
        this.asciiEscapes['<']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['>']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['&']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['(']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes[')']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['#']  = CharacterEscapes.ESCAPE_CUSTOM;
        this.asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
        
        // 2. 커스터 마이징 문자 SET
        Map<CharSequence, CharSequence> lookupMap = new HashMap<CharSequence, CharSequence>();
//        lookupMap.put("(", "(");
//        lookupMap.put(")", ")");
//        lookupMap.put("#", "#");
//        lookupMap.put("\'", "\'");
        
        // 3. XSS 방지 처리 특수 문자 인코딩 값 지정
        this.translator = new AggregateTranslator(
		        				new LookupTranslator(EntityArrays.BASIC_ESCAPE) 			// <, >, &, " 는 여기에 포함됨
		        			  , new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE)
		        			  , new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE)
		        			  , new LookupTranslator(lookupMap)								// 여기에서 커스터마이징 가능
        );
    }
    
	@Override
	public int[] getEscapeCodesForAscii() {
		// log.debug("[F] getEscapeCodesForAscii : {}", this.asciiEscapes);
		return this.asciiEscapes;
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		// return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
		log.debug("[F] getEscapeCodesForAscii : {}", this.translator.translate(Character.toString((char) ch)));
		return new SerializedString(this.translator.translate(Character.toString((char) ch)));
	}

}
