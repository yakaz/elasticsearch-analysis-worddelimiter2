package org.elasticsearch.index.analysis;

import org.testng.annotations.Test;

@Test
public class IntegrationTest extends BaseESTest {

    public static final String ANALYZER = "configured_analyzer";

    @Test
    public void testAnalysis() {
        assertAnalyzesTo(ANALYZER, "my wi-fi sucks",
                new String[]{"my", "wi-fi", "wi", "fi", "wifi", "sucks"},
                new int[]{      0,       3,    3,    6,      3,       9},
                new int[]{      2,       8,    5,    8,      8,      14},
                null,
                new int[]{      1,       1,    0,    0,      0,       1},
                null, false);
        assertAnalyzesTo(ANALYZER, "a-b'c@d.e",
                new String[]{"a-b'c@d.e", "a", "b", "c", "d", "e", "abcde"},
                new int[]{             0,   0,   2,   4,   6,   8,       0},
                new int[]{             9,   1,   3,   5,   7,   9,       9},
                null,
                new int[]{             1,   0,   0,   0,   0,   0,       0},
                null, false);
    }

}
