/*
    package aaabellski.dwq;

    metaQuery aaa {
        sqlMetadata = ru.bellski.metadata.examples.domain.UserSqlMetadata;
        parameters = [
            String q,
            AA f
        ];
        
        return List<Boolean>;
    }
*/

SELECT *
FROM a
WHERE b = ? OR ?
