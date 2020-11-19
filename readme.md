##batchManagement cli
`./batchManager listJobs --trustSslCertificates --batchManager=localhost:9443 --user=chris --password=chrispwd`

##batchManagement REST 
https://localhost:9443/ibm/api

## Demo volgorde
* Batchlet + starten batch
* Chunk processor
* Decision
* Listener

[PersoonItemWriter|writeItems(List\<Object\>) :void)]
[PersoonToLowerCaseProcessor|processItem(Object) : Object]
[PersoonUpperCaseItemReader|readItem() : Object]

[PersoonItemWriter|writeItems(List\<Object\>) :void) {bg:wheat}]
[PersoonToLowerCaseProcessor|processItem(Object) : Object]
[PersoonUpperCaseItemReader|readItem() : Object]

[PersoonItemWriter|writeItems(List\<Object\>) :void) {bg:wheat}]
[PersoonToUpperCaseProcessor|processItem(Object) : Object]
[PersoonLowerCaseItemReader|readItem() : Object]

[MyBatchlet|process() : String]