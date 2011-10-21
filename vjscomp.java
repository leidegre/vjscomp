import com.google.javascript.jscomp.BasicErrorManager;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.JSError;

public class vjscomp {
	static class ErrorListManager extends BasicErrorManager {
		@Override
		public void println(CheckLevel level, JSError error) {

			// See http://msdn.microsoft.com/en-us/library/yxkt8b26.aspx
            // for information about why these error message are formatted as they are

			StringBuilder sb = new StringBuilder();
			
			sb.append(error.sourceName);
			
			sb.append('(').append(error.lineNumber).append(',').append(error.getCharno()).append(')');
			
			sb.append(' ').append(':').append(' ');

			switch(level) {
				case ERROR:
					sb.append("error");
					break;
				case WARNING:
					sb.append("warning");
					break;
			}
			
			sb.append(' ');
			
			sb.append(error.getType().key);

			sb.append(':').append(' ');
			
			sb.append(error.description);

			System.err.println(sb.toString());
		}

		@Override
		protected void printSummary() {
		}
	}

	static class Runner extends CommandLineRunner {
		Runner(String[] args) {
			super(args);
		}

		@Override
		protected Compiler createCompiler() {
			return new Compiler(new ErrorListManager());
		}
	}

	public static void main(String[] args) {
		Runner runner = new Runner(args);
		if (runner.shouldRunCompiler()) {
			runner.run();
		} else {
			System.exit(-1);
		}
	}
}