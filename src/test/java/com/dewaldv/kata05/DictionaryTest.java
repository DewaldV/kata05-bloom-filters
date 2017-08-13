package com.dewaldv.kata05;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DictionaryTest {

    @Test
    public void shouldAddItemToSet() {
        Dictionary set = new Dictionary();

        set.add("test");
        boolean contains = set.contains("test");

        assertThat(contains, is(true));
    }

    @Test
    public void shouldNotReportItemInSetWhenNotAdded() {
        Dictionary set = new Dictionary();
        set.add("test1");
        set.add("test2");

        assertThat(set.contains("test1"), is(true));
        assertThat(set.contains("test2"), is(true));
        assertThat(set.contains("test3"), is(false));
    }

    @Test
    public void shouldCheckWordsInDictionary() throws Exception {
        Path unixDict = Paths.get("/usr/share/dict/words");

        Dictionary dictionary = new Dictionary();

        try (Stream<String> lines = Files.lines(unixDict)) {
            lines.forEach(dictionary::add);
        }

        assertThat(dictionary.contains("hyper"), is(true));
        assertThat(dictionary.contains("special"), is(true));
        assertThat(dictionary.contains("wonderful"), is(true));
        assertThat(dictionary.contains("blaargh1"), is(false));
        assertThat(dictionary.contains("arrrgh"), is(false));
        assertThat(dictionary.contains("definitelynotinthedictionary"), is(false));
    }
}