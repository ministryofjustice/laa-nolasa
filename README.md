# laa-nolasa
The LAA's Not On Libra AutoSearch Application. Microservice which automatically carries out searches against Libra based on data in MLRA.

# laa-nolasa dependency:
LAA's Not On Libra AutoSearch Application is dependent on a few proceedures and tables in the laa-mlra application:

### XXMLA_NOL table 
- laa-nolasa reads cases from here which it uses to search LIBRA. This is a soft dependency.

### XXMLA_AUTOSEARCH_RESULTS table 
- this table needs to be present in the database as laa-nolasa writes to it, after searching LIBRA

### XXMLA_NOL_STATUSES table 
- this reference table has been amended to add new statuses which laa-nolasa requires to perform searches.

### XXMLA_EVENTS_PACKAGE - update_nol_record() proceedure
- amended to facilitate updating of nol records after laa-nolasa searches
