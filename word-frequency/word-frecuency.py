import re
from collections import Counter


def count_word_frequency(path: str) -> Counter:
    counter = Counter()

    with open(path, "r", encoding="utf-8") as file:
        for line in file:
            words = re.findall(r"\b\w+\b", line.lower())
            counter.update(words)

    return counter


def print_top_words(word_frequency: Counter, top_n: int = 10):
    print("\nTop 10 most frequent words:")
    for word, frequency in word_frequency.most_common(top_n):
        print(f"{word}: {frequency}")


def main():
    try:
        word_frequency = count_word_frequency("file.txt")
        print_top_words(word_frequency)
    except FileNotFoundError:
        print("Error: file.txt not found.")


if __name__ == "__main__":
    main()