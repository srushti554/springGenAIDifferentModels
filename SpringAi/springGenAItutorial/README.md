This Readme will have all the notes for Spring AI


RAG:Retrieval Augmented Generation
Basic process: Documents -> Chunks -> Tokens -> Embeddings
1. Ingestion: Documents or company data are loaded and split into smaller chunks, often stored in a vector database.
2. Retrieval: When you ask a question, the system searches the external database for the most relevant text snippets
3. Augmentation: The system combines your original question with the retrieved text chunks into a single prompt.
4. Generation: The LLM reads the augmented prompt and writes an answer based on that specific context.