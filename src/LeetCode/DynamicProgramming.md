# Dynamic programming
---

## 动态规划
** 先解决子问题，再逐步解决大问题 **  
** 仅当每个子问题都是离散的，即不依赖于其他子问题时，动态规划才管用。**


## 背包问题
一个可装4磅东西的背包，有如下三件商品，为了让背包中的商品价值最高，该选择哪些商品？  

![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/E672A14F42F44330B9C9113585CC8E8B/7661)

每个动态规划算法都从一个网格开始：  

- **单元格中的值通常就是你要优化的值**
- **每个单元格都是一个子问题，应考虑如何将问题分成子问题，这有助于找出网格坐标轴**

背包问题的网格如下：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/67A52C720FDA497898DEEAF5E116221D/7664)
填充网格，每个单元格都将包含当前可装入背包的所有商品，每一行表示的是当前的最大价值：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/8C33A7B6AD7946FE93A3E988A01AE081/7666)
在每一行，可放入的商品都为当前行的商品以及之前各行的商品：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/FBC54EF7DEA94437ADC64D02FA4ADE31/7668)  
更新最大价值：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/EDE2736876EC4EB696E540DD00CC9B6A/7670)
余下了空间时，可根据子问题的答案来确定余下的空间可装入哪些商品，最终的网格如下：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/736BA4F7B55447FE963916CEC80436AB/7672)  
每个单元格的计算公式如下：
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/65D1770FBA7E4C01819C2DE438851490/7674)

## 最长公共子串

找出两个单词的最长公共子串，例如查找HISH和FISH的最长子串的网格如下：  
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/A0D2730DF46B4CAE84D63E16102690B9/7676)
对于最长公共子串问题，答案为网格中最大的数字，它可能并不位于最后的单元格中，例如查找hish和vista的最长公共子串：  
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/0C59677F2E33413EBF6AA8D83077CD77/7678)



## 最长公共子序列

最长公共子序列：两个单词中都有的序列包含的字母数。例如查找FOSH和FISH的最长公共子序列的网格如下：  
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/BAA8CA5D068B46478417BE5598067984/7681)  
填写各个单元格时使用的公式：  
![](https://note.youdao.com/yws/public/resource/6188944ea20037ffb98fd1f9bfde881f/xmlnote/6CA52A52ABBC43E5B99CA66EA2AC9BEC/7683)  


	public int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null)  return 0;
		int[][] dp = new int[text1.length()+1][text2.length()+1];        
		for (int i = 1; i <= text1.length(); i++) {
			for (int j = 1; j <= text2.length(); j++) {
			    if (text1.charAt(i-1) == text2.charAt(j-1)) { 
			        dp[i][j] = dp[i - 1][j - 1] + 1; 
			    } else {			
			        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);                    
			    }
			}
		}
		return dp[text1.length()][text2.length()];
	}

 



