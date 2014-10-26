package serializers

import org.joda.time.DateTime
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.core.JsonParser
import java.text.SimpleDateFormat
import java.util.Date
import com.fasterxml.jackson.databind.JsonDeserializer

class CustomIdCardDateDeserializer extends JsonDeserializer[Date]{
	@Override
        def deserialize(jp:JsonParser, ctxt:DeserializationContext):Date ={
		var formatter = new SimpleDateFormat("MM-dd-yyyy")
		try{
			return formatter.parse(jp.getText())
		}
		catch{
			case exception: Exception => return null
		}
	}
	
}
