package serializers

import org.joda.time.DateTime
import java.util.Date
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.lang.Integer 

class CustomUserIdPutSerializer extends JsonSerializer[Int]{
	@Override
	def serialize(value: Int, jgen: JsonGenerator, provider: SerializerProvider): Unit = {
		var value_string = value + ""		
		var formatter =  value_string.substring(1)
		jgen.writeString(""+Integer.parseInt(formatter))                
		//jgen.writeString(formatter)
	}

}
