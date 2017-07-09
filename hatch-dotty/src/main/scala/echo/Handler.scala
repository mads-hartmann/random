package echo

import java.io.InputStream;
import java.io.OutputStream;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;

object Handler {
  def handle(in: InputStream, out: OutputStream, context: Context): Unit = {
    var letter = in.read
    out.write("Hello from Dotty 0.1.2-RC1".getBytes)
    while(letter != -1) {
      out.write(Character.toUpperCase(letter));
      letter = in.read
    }
  }
}
