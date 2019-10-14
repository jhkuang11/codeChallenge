import java.util.*;

class Trie {

	class TrieNode {
		TrieNode[] children;
		boolean isEnd = false;

		TrieNode() {
			children = new TrieNode[26];
		}
	}

	private TrieNode root;

	Trie() {
		root = new TrieNode();
	}

	public void add(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char cur = word.charAt(i);
			if (node.children[cur - 'a'] == null) {
				node.children[cur - 'a'] = new TrieNode();
			}
			node = node.children[cur - 'a'];
		}
		node.isEnd = true;
	}

	private TrieNode searchPrefix(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char cur = word.charAt(i);
			if (node.children[cur - 'a'] != null) {
				node = node.children[cur - 'a'];
			} else {
				return null;
			}
		}
		return node;
	}

	public boolean searchWord(String word) {
		TrieNode node = searchPrefix(word);
		return node.isEnd;
	}

	public boolean isPrefix(String word) {
		TrieNode node = searchPrefix(word);
		return node != null && !node.isEnd;
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.add("coding");
		trie.add("pratice");
		String test = "codin";
		System.out.print("Is " + test + " a prefix?: ");
		System.out.println(trie.isPrefix(test));
		System.out.print("Is " + test + " a word?: ");
		System.out.println(trie.searchWord(test));
	}
}