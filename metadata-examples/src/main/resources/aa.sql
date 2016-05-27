/*
    ReturnRule = List;
    Metadata = ru.bellski.metadata.examples.domain.AddressSqlMetadata;

    Parameters = [
        String param1,
        String param3
    ]
*/

SELECT *
FROM a
WHERE b = ? OR ?
