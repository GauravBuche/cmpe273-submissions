package serializers

import org.joda.time.DateTime
import java.util.Date
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import com.fasterxml.jackson.databind.JsonDeserializer

class CustomIdCardDateSerializer extends JsonSerializer[Date]{
	@Override
        def serialize(value: Date, jgen: JsonGenerator,provider: SerializerProvider): Unit ={
		
		/*if(value==" 	"){
			jgen.writeString("unknown")
		}else{
		*/		
			var formatter = new SimpleDateFormat("MM-dd-yyyy")
			var date = formatter.format(value)
			jgen.writeString(date)
		//}
		
	}
	
}
