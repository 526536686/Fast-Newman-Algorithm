package com.cse.newman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cse.graph.Community;
import com.cse.graph.Node;

/**
 * Fast Newman 算法
 * 
 * @author 作者 E-mail:
 * @date 创建时间： 2016-3-17 下午3:21:36
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class NewManAlg {
	// 需要社区发现的原始社区图
	Community community;

	public NewManAlg() {

	}

	public NewManAlg(Community community) {
		this.community = community;
	}

//	/**
//	 * 旧的计算方法，是将边长的权重完全分配给一方
//	 * 计算两个社区合并所产生的detaQ的值 detaQ = eij + eji − 2aiaj = 2(eij − aiaj),
//	 * 
//	 * @param culsterI
//	 *            一个cluster中包含的所有节点Id
//	 * @param clusterJ
//	 *            一个cluster中包含的所有节点
//	 * @return
//	 */
//	public double deltaQ(List<Integer> clusterI, List<Integer> clusterJ) {
//		// 首先获取两个cluster中的所有ID
//		Set<Integer> clusterI_id = new HashSet<Integer>();
//		// for (Node node : clusterI) {
//		// clusterI_id.add(node.getId());
//		// }
//		clusterI_id.addAll(clusterI);
//
//		Set<Integer> clusterJ_id = new HashSet<Integer>();
//		// for (Node node : clusterJ) {
//		// clusterJ_id.add(node.getId());
//		// }
//		clusterJ_id.addAll(clusterJ);
//
//		// 首先获取eij的值
//		double eij = 0;
//
//		// 保存已经计算的节点对，防止重复计算，因为论文中有tips
//		/**
//		 * each edge should contribute only to eij once, either above or below
//		 * the diagonal, but not both. Alternatively, and more elegantly, one
//		 * can split the contribution of each edge half-and-half between eij and
//		 * eji, except for those edges that join a group to itself, whose
//		 * contribution belongs entirely to the single diagonal element eii for
//		 * the group in question.
//		 */
//		Set<String> innerNodeSet = new HashSet<String>();
//
//		// 循环遍历两个集合，生成最终的eij
//		for (int i : clusterI_id) {
//			for (int j : clusterJ_id) {
//				/**
//				 * 若已经计算过了，则忽略
//				 */
//				if (innerNodeSet.contains(i + "" + j)
//						|| innerNodeSet.contains(j + "" + i)) {
//					continue;
//				}
//				innerNodeSet.add(i + "" + j);
//				innerNodeSet.add(j + "" + i);
//				double e_tmp = community.getWeight(i, j);
//				if (e_tmp != Double.MIN_VALUE) {
//					eij += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
//				} else {
//					continue;
//				}
//			}
//		}
//
//		// 获取和clusterI内所有节点相邻的节点上的权重
//		// 生成最终的ai
//		// 保存与clusterI相邻的所有节点
//		Set<Integer> clusterI_set = new HashSet<Integer>();
//		// for (Node node : clusterI) {
//		for (int nodeId : clusterI_id) {
//			// System.out.println(community.getNodeList());
//			Node tmp = community.getNodeById(nodeId);
//			Set<Integer> neiborId = tmp.getAllNeibor();
//
//			// 全部保存至相邻的所有节点set中
//			clusterI_set.addAll(neiborId);
//		}
//		for (int nodeId : clusterI_id) {
//			// 将簇内部中的节点删除，防止内包含
//			clusterI_set.remove(nodeId);
//		}
//		// System.out.println("clusterI邻居节点有：" + clusterI_set);
//
//		// 保存与clusterJ相邻的所有节点
//		Set<Integer> clusterJ_set = new HashSet<Integer>();
//		// for (Node node : clusterJ) {
//		for (int nodeId : clusterJ_id) {
//			Set<Integer> neiborId = community.getNodeById(nodeId)
//					.getAllNeibor();
//
//			// 全部保存至相邻的所有节点set中
//			clusterJ_set.addAll(neiborId);
//		}
//		for (int nodeId : clusterJ_id) {
//			// 将簇内部中的节点删除，防止内包含
//			clusterJ_set.remove(nodeId);
//		}
//		// System.out.println("clusterJ邻居节点有：" + clusterJ_set);
//
//		// 保存已经计算的节点对，防止重复计算，因为论文中有tips
//		Set<String> calcuAi = new HashSet<String>();
//		double ai = 0;
//		for (int i : clusterI_id) {
//			for (int j : clusterI_set) {
//				if (calcuAi.contains(i + "" + j)
//						|| calcuAi.contains(j + "" + i)) {
//					continue;
//				} else {
//					calcuAi.add(i + "" + j);
//					calcuAi.add(j + "" + i);
//					double e_tmp = community.getWeight(i, j);
//					if (e_tmp != Double.MIN_VALUE) {
//						ai += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
//					} else {
//						continue;
//					}
//				}
//			}
//		}
//
//		// 计算aj
//		double aj = 0;
//		for (int i : clusterJ_id) {
//			for (int j : clusterJ_set) {
//				if (calcuAi.contains(i + "" + j)
//						|| calcuAi.contains(j + "" + i)) {
//					continue;
//				} else {
//					calcuAi.add(i + "" + j);
//					calcuAi.add(j + "" + i);
//					double e_tmp = community.getWeight(i, j);
//					if (e_tmp != Double.MIN_VALUE) {
//						aj += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
//					} else {
//						continue;
//					}
//				}
//			}
//		}
//		// System.out.println("eij: " + eij + " ai: " + ai + " aj: " + aj);
//		double detaQ = 2 * (eij - ai * aj);
//
//		return detaQ;
//
//	}
	
	/**
	 * 新的计算方法，一点占一半的权重
	 * 计算两个社区合并所产生的detaQ的值 detaQ = eij + eji − 2aiaj = 2(eij − aiaj),
	 * 
	 * @param culsterI
	 *            一个cluster中包含的所有节点Id
	 * @param clusterJ
	 *            一个cluster中包含的所有节点
	 * @return
	 */
	public double deltaQ(List<Integer> clusterI, List<Integer> clusterJ) {
		// 首先获取两个cluster中的所有ID
		Set<Integer> clusterI_id = new HashSet<Integer>();
		clusterI_id.addAll(clusterI);

		Set<Integer> clusterJ_id = new HashSet<Integer>();
		clusterJ_id.addAll(clusterJ);

		// 首先获取eij的值
		double eij = 0;

		// 保存已经计算的节点对，防止重复计算，因为论文中有tips
		/**
		 * each edge should contribute only to eij once, either above or below
		 * the diagonal, but not both. Alternatively, and more elegantly, one
		 * can split the contribution of each edge half-and-half between eij and
		 * eji, except for those edges that join a group to itself, whose
		 * contribution belongs entirely to the single diagonal element eii for
		 * the group in question.
		 */
		Set<String> innerNodeSet = new HashSet<String>();

		// 循环遍历两个集合，生成最终的eij
		for (int i : clusterI_id) {
			for (int j : clusterJ_id) {
				/**
				 * 若已经计算过了，则忽略
				 */
				if (innerNodeSet.contains(i + "" + j)
						|| innerNodeSet.contains(j + "" + i)) {
					continue;
				}
				innerNodeSet.add(i + "" + j);
				innerNodeSet.add(j + "" + i);
				double e_tmp = community.getWeight(i, j);
				if (e_tmp != Double.MIN_VALUE) {
					eij += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
				} else {
					continue;
				}
			}
		}

		// 获取和clusterI内所有节点相邻的节点上的权重
		// 生成最终的ai
		// 保存与clusterI相邻的所有节点
		Set<Integer> clusterI_set = new HashSet<Integer>();
		for (int nodeId : clusterI_id) {
			// System.out.println(community.getNodeList());
			Node tmp = community.getNodeById(nodeId);
			Set<Integer> neiborId = tmp.getAllNeibor();

			// 全部保存至相邻的所有节点set中
			clusterI_set.addAll(neiborId);
		}
		for (int nodeId : clusterI_id) {
			// 将簇内部中的节点删除，防止内包含
			clusterI_set.remove(nodeId);
		}
		// System.out.println("clusterI邻居节点有：" + clusterI_set);

		// 保存与clusterJ相邻的所有节点
		Set<Integer> clusterJ_set = new HashSet<Integer>();
		// for (Node node : clusterJ) {
		for (int nodeId : clusterJ_id) {
			Set<Integer> neiborId = community.getNodeById(nodeId)
					.getAllNeibor();

			// 全部保存至相邻的所有节点set中
			clusterJ_set.addAll(neiborId);
		}
		for (int nodeId : clusterJ_id) {
			// 将簇内部中的节点删除，防止内包含
			clusterJ_set.remove(nodeId);
		}
		// System.out.println("clusterJ邻居节点有：" + clusterJ_set);

		// 保存已经计算的节点对，防止重复计算，因为论文中有tips
		double ai = 0;
		for (int i : clusterI_id) {
			for (int j : clusterI_set) {
				if (clusterJ.contains(j)){
					double e_tmp = community.getWeight(i, j);
					if (e_tmp != Double.MIN_VALUE) {
						ai += e_tmp/2.0; // 找到了两个簇之间的连接边，则将权重加上去
					} else {
						continue;
					}
				} else {
					double e_tmp = community.getWeight(i, j);
					if (e_tmp != Double.MIN_VALUE) {
						ai += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
					} else {
						continue;
					}
				}
			}
		}

		// 计算aj
		double aj = 0;
		for (int i : clusterJ_id) {
			for (int j : clusterJ_set) {
				if (clusterI.contains(j)){
					double e_tmp = community.getWeight(i, j);
					if (e_tmp != Double.MIN_VALUE) {
						aj += e_tmp/2.0; // 找到了两个簇之间的连接边，则将权重加上去
					} else {
						continue;
					}
				} else {
					double e_tmp = community.getWeight(i, j);
					if (e_tmp != Double.MIN_VALUE) {
						aj += e_tmp; // 找到了两个簇之间的连接边，则将权重加上去
					} else {
						continue;
					}
				}
			}
		}
		// System.out.println("eij: " + eij + " ai: " + ai + " aj: " + aj);
		double detaQ = 2 * (eij - ai * aj);

		return detaQ;

	}

	public static void main(String[] args) {
		Community d = new Community(6);

		d.insertEdge(1, 2, 0.1);
		d.insertEdge(1, 3, 0.1);
		d.insertEdge(2, 1, 0.1);
		d.insertEdge(2, 3, 0.1);
		d.insertEdge(2, 4, 0.4);
		d.insertEdge(3, 1, 0.1);
		d.insertEdge(3, 2, 0.1);
		d.insertEdge(4, 2, 0.4);
		d.insertEdge(4, 5, 0.1);
		d.insertEdge(4, 6, 0.1);
		d.insertEdge(5, 4, 0.1);
		d.insertEdge(5, 6, 0.1);
		d.insertEdge(6, 4, 0.1);
		d.insertEdge(6, 5, 0.1);

		List<Integer> clusterI = new ArrayList<Integer>();
		clusterI.add(2);
		// clusterI.add(4);
		List<Integer> clusterJ = new ArrayList<Integer>();
		clusterJ.add(4);

		NewManAlg newManAlg = new NewManAlg(d);
		System.out.println(newManAlg.deltaQ(clusterI, clusterJ));
	}
}
