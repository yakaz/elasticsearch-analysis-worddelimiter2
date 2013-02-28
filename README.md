Elasticsearch Word Delimiter 2 Filter
=====================================

The WordDelimiterFilter2 analysis plugin provides with an extension over the mainstream WordDelimiterFilter.

Installation
-----------

Simply run at the root of your ElasticSearch v0.20.2+ installation:

	bin/plugin -install com.yakaz.elasticsearch.plugins/elasticsearch-analysis-worddelimiter2/1.1.0

This will download the plugin from the Central Maven Repository.

For older versions of ElasticSearch, you can still use the longer:

	bin/plugin -url http://oss.sonatype.org/content/repositories/releases/com/yakaz/elasticsearch/plugins/elasticsearch-analysis-worddelimiter2/1.1.0/elasticsearch-analysis-worddelimiter2-1.1.0.zip install elasticsearch-analysis-worddelimiter2

In order to declare this plugin as a dependency, add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>com.yakaz.elasticsearch.plugins</groupId>
    <artifactId>elasticsearch-analysis-worddelimiter2</artifactId>
    <version>1.1.0</version>
</dependency>
```

Version matrix:

	-------------------------------------------------
	| HashSplitter Analysis Plugin | ElasticSearch  |
	-------------------------------------------------
	| master                       | 0.90 -> master |
	-------------------------------------------------
	| 1.1.0                        | 0.90 -> master |
	-------------------------------------------------
	| 1.0.1                        | 0.19 -> 0.20   |
	-------------------------------------------------
	| 1.0.0                        | 0.19 -> 0.20   |
	-------------------------------------------------

Description
-----------

Lucene 4's WordDelimiterFilter is exposed since ElasticSearch v0.17.0.
This plugin exposes an extension over this filter, packaged as an ElasticSearch 0.19.0+ plugin.

See [Lucene WordDelimiterFilter JavaDoc][WDFJavadoc] for more information about the base functionality.

Added features
--------------

Currently there is a single added feature:

* __All parts at same position__

  When the filter splits the input token, it generates additional tokens, usually each of them takes a new position on its own.
  If you ask to preserve the original token and to catenate some parts, you may get multiple times identical tokens, at diverse positions.
  The catenated tokens are added at the final position, making multiple tokens at the same position.
  All this behavior is a little confusing.

  This new feature permits to output all tokens at the same position, hence "turn wi-fi on" with catenate numbers and preserve original will no longer yield `0:turn 1:wi-fi 1:wi 2:fi 2:wifi 3:on`, but will yield `0:turn 1:wi-fi 1:wi 1:fi 1:wifi 2:on`.

  This is particularly useful when merging with other analysis, using the [Combo Analyzer][Combo], to prevent position jitter.

  Please always be aware of the impact of terms positions with regard to your queries.

Configuration
-------------

The plugin provides you with the `word_delimiter_2` token filter type.
It accepts the [same list of parameters as the `word_delimiter` token filter][WDFEsDoc], plus:

* `all_parts_at_same_position`: `false` by default.


See also
--------

[ElasticSearch WordDelimiterFilter doc][WDFEsDoc]

[Lucene WordDelimiterFilter JavaDoc][WDFJavadoc]

[Combo Analyzer plugin][Combo]



[WDFEsDoc]: http://www.elasticsearch.org/guide/reference/index-modules/analysis/word-delimiter-tokenfilter.html
    (ElasticSearch WordDelimiterFilter doc)

[WDFJavadoc]: http://lucene.apache.org/core/4_0_0/analyzers-common/org/apache/lucene/analysis/miscellaneous/WordDelimiterFilter.html
    (Lucene WordDelimiterFilter JavaDoc)

[Combo]: https://github.com/yakaz/elasticsearch-analysis-combo/
    (Combo Analyzer plugin)
