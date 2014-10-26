package serializers

import org.joda.time.DateTime
import java.util.Date
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat

class CustomWebLoginIdSerializer extends JsonSerializer[Int]{
	@Override
	def serialize(value: Int, jgen: JsonGenerator, provider: SerializerProvider): Unit = {
		var formatter =  "l-"+ value
		                
		jgen.writeString(formatter)
	}

}
