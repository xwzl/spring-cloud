**问题分解**

我们把算术表达式输入给计算器程序并得到最终的计算结果，背后应存在三个过程：

- 算术表达式合法性检查
- 解析算术表达式
- 计算并输出结果

### 算术表达式合法性检查

合法性检查即对用户输入的算式进行检验，判断其结构和语义是否存在不合法。例如我们期望的用户输入的合法表达式如下：

    1+1
    10*2-10 
    20*20+10/5 
    (10+30)/(20-5)

而不合法的表达式输入会是这样：

    10*2/0
    (10+30)/(20-5
    2+3*
    2*-10
 
诸如此类，我们可以从中总结一些有关表达式合法与否的规则：
 
- 括号必定成对出现
-  除数不能为0
- 运算符不能出现在表达式开始或末尾，也不能出现在左括号的右侧或右括号的左侧。
- 运算符不能连续出现

为简化问题，不妨假设程序输入端只会接收到+ - * / ( )和数字。接下来我们一步一步解决该问题。

#### 括号必须成对出现

回到check()方法，我们先考虑括号必须成对出现的规则。这里我们可以使用多种方法来检验。

方法一：

我们可以设置一个计数器且初值为0，在遍历数组时，每当读取到(，计数器+1；读取到)，计数器-1。
任何时候计数器的值都不能小于0，且遍历结束后计数器的值必须也随即归0，否则代表括号未成对出现，即表达式不合法。

方法二：

我们可以使用栈(Stack)来解决该问题。在遍历数组时，每当读取到(，将其压入栈；每当读到)，将栈中的(弹出栈。
如果当读取到)时栈为空，或遍历结束后栈未空，代表括号未成对出现，即表达式不合法。

```
/**
 * 表达式合法性检验
 *
 * @throws FormatException
 */
private void check() throws FormatException {
    // 使用Stack变量来存储左右括号的出现
    Stack<Character> brackets = new Stack<>();
    // 将算术表达式转换成字符数组
    char[] chars = expression.toCharArray();
    // 循环遍历整个字符数组
    for (int i = 0; i < chars.length; i++) {
        switch (chars[i]) {
            case '(':
                // 遇到`(`压入栈
                brackets.push(chars[i]);
                break;
            case ')':
                // 遇到`)`弹出栈，若栈空，则代表括号未成对出现，抛出异常
                if (brackets.empty() || brackets.pop() != '(') throw new FormatException("算术表达式不合法");
                break;
        }
    }
    // 若遍历结束后，栈未空，则代表括号未成对出现，抛出异常
    if (!brackets.empty()) throw new FormatException("算术表达式不合法");
}
```

**除数不能为0**

关于这条规则，我们可以引入一个局部变量lastChar，用来存储上一个出现的字符，不包括左右括号。如果循环遍历到字符0时，程序即开始检验lastChar中的值是否为/，若是则抛出异常，即除数不能为0。

```
/**
 * 表达式合法性检验
 *
 * @throws FormatException
 */
private void check() throws FormatException {
    // 使用Stack变量来存储左右括号的出现
    Stack<Character> brackets = new Stack<>();
    // 上一个出现的字符，不含括号
    char lastChar = ' ';
    // 将算术表达式转换成字符数组
    char[] chars = expression.toCharArray();
    // 循环遍历整个字符数组
    for (int i = 0; i < chars.length; i++) {
        switch (chars[i]) {
            case '(':
                // 遇到`(`压入栈
                brackets.push(chars[i]);
                break;
            case '+':
            case '-':
            case '*':
            case '/':
                // 存储这次读取到的运算符
                lastChar = chars[i];
                break;
            case '0':
                if (lastChar == '/') throw new FormatException("除数不能为0");
                break;
            case ')':
                // 遇到`)`弹出栈，若栈空，则代表括号未成对出现，抛出异常
                if (brackets.empty() || brackets.pop() != '(') throw new FormatException("算术表达式不合法");
                break;
            default:
                // 存储这次读取到的运算符
                lastChar = chars[i];
                break;
        }
    }
    // 若遍历结束后，栈未空，则代表括号未成对出现，抛出异常
    if (!brackets.empty()) throw new FormatException("算术表达式不合法");
}
```

上述代码添加了lastChar变量，并在switch添加了新的case。遇到0时检验lastChar的值是否为/，若是则抛出异常。

**运算符出现位置**

根据规则：
- 运算符不能出现在表达式开始或末尾，也不能出现在左括号的右侧或右括号的左侧
- 运算符不能连续出现

我们可以利用下标，在循环遍历的switch分支中便捷地检验上述情况。

```
/**
 * 表达式合法性检验
 *
 * @throws FormatException
 */
private void check() throws FormatException {
    // 使用Stack变量来存储左右括号的出现
    Stack<Character> brackets = new Stack<>();
    // 上一个出现的字符，不含括号
    char lastChar = ' ';
    // 将算术表达式转换成字符数组
    char[] chars = expression.toCharArray();
    // 如果输入为空，则抛出异常
    if (chars.length == 0) throw new FormatException("输入不能为空");
    // 循环遍历整个字符数组
    for (int i = 0; i < chars.length; i++) {
        switch (chars[i]) {
            case '(':
                // 遇到`(`压入栈
                brackets.push(chars[i]);
                break;
            case ')':
                // 遇到`)`弹出栈，若栈空，则代表括号未成对出现，抛出异常
                if (brackets.empty() || brackets.pop() != '(') throw new FormatException("算术表达式不合法");
                break;
            case '+':
            case '-':
            case '*':
            case '/':
                // 如果运算符出现在开头或末尾，抛出异常
                if (i == 0 || i == chars.length - 1) throw new FormatException("算术表达式不合法");
                // 如果运算符的前一位是运算符或左括号，抛出异常
                if (chars[i - 1] == '+' || chars[i - 1] == '-' || chars[i - 1] == '*' || chars[i - 1] == '/' || chars[i - 1] == '(')
                    throw new FormatException("算术表达式不合法");
                // 如果运算符的后一位是运算符或右括号，抛出异常
                if (chars[i + 1] == '+' || chars[i + 1] == '-' || chars[i + 1] == '*' || chars[i + 1] == '/' || chars[i + 1] == ')')
                    throw new FormatException("算术表达式不合法");
                // 存储这次读取到的运算符
                lastChar = chars[i];
                break;
            case '0':
                if (lastChar == '/') throw new FormatException("除数不能为0");
                break;
            default:
                // 存储这次读取到的运算符
                lastChar = chars[i];
                break;
        }
    }
    // 若遍历结束后，栈未空，则代表括号未成对出现，抛出异常
    if (!brackets.empty()) throw new FormatException("算术表达式不合法");
}
```

### 解析算术表达式

接下来我们来分析如何解析算术表达式。

小学时我们就学过四则运算的法则，先乘除后加减，有括号先算括号里的，有多层括号先算内层括号里的。

但是有些小伙伴可能会发现，我们并不能轻而易举地处理一个字符串类型的算术表达式的运算优先级问题，即便是语言工具已经为我们提供了强大的计算能力。因为计算机并不能理解所谓四则运算优先级诸如此类的概念。

此时，我们将祭出一个武器——逆波兰表达式


### 逆波兰表达式

逆波兰表达式又叫做后缀表达式。在通常的表达式中，二元运算符总是置于与之相关的两个运算对象之间，这种表示法也称为中缀表示。波兰逻辑学家J.Lukasiewicz于1929年提出了另一种表示表达式的方法，按此方法，每一运算符都置于其运算对象之后，故称为后缀表示。

举个栗子，将通常的表达式转换成逆波兰表达式：

|通常的表达式 |逆波兰表达式|
|---:|---:|
|10 + 20|	10 20 +|
|3 * 5 - 10|	3 5 * 10 -|
|( 1 + 2 ) * ( 3 + 4 )	|1 2 + 3 4 + *|

有的小伙伴会问，转换成这样有什么好处呢。

好处就是，逆波兰表达式消除了优先级的概念，计算机只需要从左往右读取算术表达式字符串，再按照运算符规则运算即可，如下：

普通表达式转化为逆波兰表达式后，从左往右依次读取表达式的字符。如果当前字符为变量或者为数字，则压栈，如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，最后当表达式扫描完后，栈里的就是结果。

还是举个栗子，就以( 1 + 2 ) * ( 3 + 4 )为栗，当算式转换成1 2 + 3 4 + *后，运算过程如下：

- 依次读取到1和2，俩数字压入栈，此时栈中的数字从顶到底依次为2 1
- 读取到+，将栈顶的俩数字即1和2用加法进行运算，并将结果3重新压栈，此时栈中仅剩一个数字3
- 读取到3和4，俩数字压入栈，此时栈中的数字从顶到底依次为4 3 3
- 读取到+，将栈顶的俩数字即4和3用加法进行运算，并将结果7重新压栈，此时栈中的数字从顶到底依次为7 3
- 读取到*，将栈顶的俩数字即7和3用乘法进行运算，并将结果21重新压栈，此时栈中的数字仅剩21
- 21即为结果

好了，我们现在知道了逆波兰表达式的求值算法，但是问题来了：我们如何将普通的表达式转换成逆波兰表达式呢？

### 转化成逆波兰表达式

将一个普通的中序表达式转换为逆波兰表达式的一般算法是：

首先需要分配2个栈，一个作为临时存储运算符的栈S1（含一个结束符号），一个作为输入逆波兰式的栈S2（空栈），S1栈可先放入优先级最低的运算符#，注意，中缀式应以此最低优先级的运算符结束。可指定其他字符，不一定非#不可。从中缀式的左端开始取字符，逐序进行如下步骤：

1. 若取出的字符是操作数，则分析出完整的运算数，该操作数直接送入S2栈
2. 若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈，否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，最后将该运算符送入S1栈。
3. 若取出的字符是“（”，则直接送入S1栈顶。 若取出的字符是“（”，则直接送入S1栈顶。
4. 若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
5. 重复上面的1-4步，直至处理完所有的输入字符。将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
6. 完成以上步骤，S2栈便为逆波兰式输出结果。不过S2应做一下逆序处理。便可以按照逆波兰式的计算方法计算了！完成以上步骤，S2栈便为逆波兰式输出结果。不过S2应做一下逆序处理。便可以按照逆波兰式的计算方法计算了！

```
/**
 * 存储运算符优先级，value越大代表优先级越大
 */
private Map<String, Integer> map = new HashMap<>();
/**
 * 构造函数
 *
 * @param expression 算术表达式
 */
public Calculator(String expression) {
    map.put("+", 0);
    map.put("-", 0);
    map.put("*", 1);
    map.put("/", 1);
    map.put("(", -1);
    map.put(")", -1);
    map.put("#", -2); 
    this.expression = expression;
}
```

由以上算法思想我们可以逐步实现convert2RPN()方法来实现普通表达式到逆波兰表达式的转化：

```
/**
 * 转化为逆波兰式
 *
 * @return 存储在Stack中的逆波兰表达式
 */
private Stack<String> convert2RPN() {
    // 声明栈S1
    Stack<String> s1 = new Stack<>();
    // 声明栈S2
    Stack<String> s2 = new Stack<>();
    // 将最低优先级的#符号放入S1栈，为了方便统一后续操作
    s1.push("#");
    // 将算术表达式转换成字符数组
    char[] chars = expression.toCharArray();
    // 循环遍历字符数组
    for (int i = 0; i < chars.length; i++) {
        switch (chars[i]) {
            case '(':
                // 读取到左括号，直接压入S1栈
                s1.push(chars[i] + "");
                break;
            case ')':
                // 若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
                do {
                    s2.push(s1.pop());
                } while (!s1.peek().equals("("));
                s1.pop();
                break;
            case '+':
            case '-':
            case '*':
            case '/':
                //  若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，
                // 如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈，
                // 否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，
                // 最后将该运算符送入S1栈。
                if (map.get(String.valueOf(chars[i])) > map.get(s1.peek())) {
                    s1.push(chars[i] + "");
                } else {
                    do {
                        s2.push(s1.pop());
                    } while (!(map.get(chars[i] + "") > map.get(s1.peek())));
                    s1.push(chars[i] + "");
                }
                break;
            default:
                // 若取出的字符是操作数，则分析出完整的运算数
                StringBuilder sb = new StringBuilder();
                // 处理俩位以上的数以及小数的读取
                while (Character.isDigit(chars[i]) || chars[i] == '.') {
                    sb.append(chars[i]);
                    if (i < chars.length - 1 && (Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                        i++;
                    } else {
                        break;
                    }
                }
                // 该操作数直接送入S2栈
                s2.push(sb.toString());
                break;
        }
    }
    // 将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
    while (!s1.peek().equals("#")) {
        s2.push(s1.pop());
    }
    // S2应做一下逆序处理
    Stack<String> stack = new Stack<>();
    while (!s2.empty()) {
        stack.push(s2.pop());
    }
    // 返回S2的逆序栈
    return stack;
}
```

### 计算并输出结果

得到逆波兰表达式后，我们就要计算结果了。计算方法上文已经提到，这里方便大家再写一遍:

> 新建一个表达式,如果当前字符为变量或者为数字，则压栈，如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，最后当表达式扫描完后，栈里的就是结果。

另外，我们也不要忘记在计算之前调用check()方法来检验用户输入的表达式是否合法。

按照上述算法实现calculate()比较简单，具体代码如下：

```
/**
 * 计算结果
 *
 * @return 表达式计算结果
 * @throws FormatException
 */
public double calculate() throws FormatException {
    check();
    double result;
    Stack<String> tmp = new Stack<>();
    Stack<String> stack = convert2RPN();
    while (!stack.empty()) {
        String s = stack.pop();
        if (Character.isDigit(s.charAt(0))) {
            tmp.push(s);
        } else {
            double a = Double.valueOf(tmp.pop());
            double b = Double.valueOf(tmp.pop());
            switch (s) {
                case "+":
                    tmp.push(String.valueOf(add(a, b)));
                    break;
                case "-":
                    tmp.push(String.valueOf(delete(b, a)));
                    break;
                case "*":
                    tmp.push(String.valueOf(multiply(a, b)));
                    break;
                case "/":
                    tmp.push(String.valueOf(divide(b, a)));
                    break;
            }
        }
    }
    result = Double.valueOf(tmp.pop());
    return result;
}

private double add(double a, double b) {
    return a + b;
}

private double delete(double a, double b) {
    return a - b;
}

private double multiply(double a, double b) {
    return a * b;
}

private double divide(double a, double b) {
    BigDecimal b1 = new BigDecimal(a);
    BigDecimal b2 = new BigDecimal(b);
    return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
}

```
