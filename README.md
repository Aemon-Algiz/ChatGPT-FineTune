# ChatGPT Fine-Tuning Example

This is a simple example demonstrating how to fine-tune ChatGPT on a dataset using OpenAI's API. The dataset consists of documents, each containing an abstract and a set of keywords. The fine-tuning process aims to help the model generate keywords based on the given abstracts.

## Usage

1. Replace `TOKEN` in the `TrainingUtil` class with your OpenAI API key.
2. Run the `App` class, passing the path to an Excel file containing the dataset as a command-line argument.

## Project Structure

- `App`: The main class that ties everything together.
- `resources` package:
    - `Document`: Represents a document with an abstract and keywords.
    - `TrainingPrompt`: Represents a training prompt and its expected completion.
- `utils` package:
    - `DocumentUtil`: Utility class for handling document creation and data splitting.
    - `PromptUtil`: Utility class for handling training prompts.
    - `TrainingUtil`: Utility class for fine-tuning ChatGPT using OpenAI's API.