package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;





public class StackWithMax {
	
	private static class MaxWithCount {
		
		public Integer max;
		public Integer count;
		
		public MaxWithCount(Integer max, Integer count) {
			this.max = max;
			this.count = count;
		}
		
	}

  public static class Stack {
	  	
	private Deque<Integer> stack = new LinkedList<>();
	private Deque<MaxWithCount> maxWithCount = new LinkedList<>();
	  
	public boolean empty() {
		
		if(stack.isEmpty())
			return true;
		return false;
	}
	public Integer max() {
	  
		return maxWithCount.peek().max;
	}
	public Integer pop() {
	  
		if(empty())
			throw new IllegalStateException();
		Integer ans = stack.pop();
		if(ans.equals(max())) {
			if(maxWithCount.peek().count.equals(1)) {
				maxWithCount.pop();
			}
			else {
				maxWithCount.peek().count--;
			}
		}
		return ans;
	}
	public void push(Integer x) {
	  
		stack.push(x);
		if(maxWithCount.isEmpty()) {
			maxWithCount.push(new MaxWithCount(x, 1));
		}
		else {
			int max = maxWithCount.peek().max;
			if(x > max) {
				maxWithCount.push(new MaxWithCount(x, 1));
			}
			else if(x.equals(max)) {
				maxWithCount.peek().count++;
			}
		}
		return;
	}
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTest(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
