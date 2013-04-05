import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String args[]) {
		Pattern patt = createPattern("^(\\d+)\\_DP\\_MBA\\_\\d+\\_\\d+\\_(\\d+EXE\\d+),((\\p{L}|\\.|\\d)+).+");
		Matcher matcher = patt
				.matcher("8110100088_DP_MBA_250412152714_83029479_15EXE46002012,usneseníč.l.20_V0000041147055517.PDF");

		if (matcher.matches()) {
			System.out.println(matcher.group(3));
		} else {
			System.out.println("fail");
		}
	}

	private static Pattern createPattern(String regex) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}
}
