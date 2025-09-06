#include <iostream>
#include <string>
#include <limits>//для std::numeric_limits

using namespace std;

//Интерфейсный класс
class TreeActions
{
public:
    virtual ~TreeActions()
    {
        cout<<"Destr TreeActions"<<endl;
    }
    virtual void photosynthesis() const=0;
    virtual void AbsorbWater() const = 0;
};
//Родительский класс
class Tree:public TreeActions
{
protected:
    string name;
    int age;
    double height;
    static int ObjCount;
public:
    Tree()
    {
        cout<<"Default Constr Tree"<<endl;
        name=" ";
        age=0;
        height=0;
        ObjCount++;
    }
    Tree(string Name,int a,double h)
    {
        cout<<"Initializing constr Tree"<<endl;
        name=Name;
        if (a<=0)
        {
            cout<<"Age must be > 0"<<endl;
            age=0;
        }
        else
        {
            age=a;
        }
        if (h<=0)
        {
            cout<<"Height must be  > 0"<<endl;
            height=0;
        }
        else
        {
            height=h;
        }
        ObjCount++;
    }
    Tree(const Tree& other)
    {
        cout<<"Copy Constr Tree"<<endl;
        name=other.name;
        age=other.age;
        height=other.height;
        ObjCount++;
    }
    virtual ~Tree()
    {
        cout<<"Destr Tree"<<endl;
        ObjCount--;
    }
    virtual void grow(int years)
    {
        if (years <=0)
        {
            cout<<"Tree will not grow because grow years <=0"<<endl;
            years=0;
        }
        else
        {
            age +=years;
            height +=years * 2;
            cout<<name<<" -> grow"<<" \nAge -> "<<age<<" \nHeight -> "<<height<<endl;
        }

    }
    virtual void Show()
    {
        cout<<"Name - "<<name<<endl;
        cout<<"Age - "<<age<<endl;
        cout<<"Height - "<<height<<endl;
    }
    void photosynthesis() const
    {
        cout<<name<<" - Performs Photosynthesis"<<endl;
    }
    void AbsorbWater() const
    {
        cout<<name<<" - AbsorbWater"<<endl;
    }
    static int Get_ObjCount()
    {
        return ObjCount;
    }
    static double DiameterTree(double treeHeight)
    {
        if (treeHeight <=0)
        {
            cout<<"DiameterTree <=0"<<endl;
            return 0;
        }
        else
        {
            cout<<"static DiameterTree - "<<treeHeight*0.75<<endl;
        }
        return treeHeight*0.75;
    }
    string Get_Name()
    {
        return name;
    }
    int Get_Age()
    {
        return age;
    }
    double Get_Height()
    {
        return height;
    }
    friend std::ostream& operator<<(std::ostream& out,Tree* tree);
    friend std::istream& operator>>(std::istream& in,Tree* &tree);

};

int Tree::ObjCount=0;

std::ostream& operator<<(std::ostream& out,Tree* tree)
{
    out<<"Tree operator - "<<tree->name<<"\nAge - "<<tree->age<<"\nHeight - "<<tree->height<<endl;
    return out;
}
std::istream& operator>>(std::istream& in,Tree* &tree)
{
    cout<<"Enter name"<<endl;
    in>>tree->name;
    int temp_age;
    while (true)
    {
        cout<<"Enter age"<<endl;
        in>>temp_age;
        if(in.fail())
        {
            cout<<"-------"<<endl;
            cout<<"Wrong enter"<<endl;
            in.clear();
            in.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
            cout<<"-------"<<endl;

        }
        else if(temp_age<=0)
        {
            cout<<"-------"<<endl;
            cout<<"Age <=0"<<endl;
            cout<<"-------"<<endl;
        }
        else
        {
            tree->age= temp_age;
            break;
        }
    }
    double temp_height;
    while (true)
    {
        cout<<"Enter height"<<endl;
        in>>temp_height;
        if(in.fail())
        {
            cout<<"-------"<<endl;
            cout<<"Wrong enter"<<endl;
            in.clear();
            in.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
            cout<<"-------"<<endl;

        }
        else if(temp_height<=0)
        {
            cout<<"-------"<<endl;
            cout<<"Height <=0"<<endl;
            cout<<"-------"<<endl;
        }
        else
        {
            tree->height=temp_height;
            break;
        }
    }
    return in;
}
class ConiferousTree:public Tree//хвойное дерево
{
protected:
    string LeafShape;
public:
    ConiferousTree(string Name,int a,double h,string LShape):Tree(Name,a,h)
    {
        cout<<"Initializing ConiferousTree"<<endl;
        /*name=Name;
        age=a;
        height=h;*/
        LeafShape=LShape;
    }
    ConiferousTree(const ConiferousTree& other):Tree(other)
    {
        cout<<"Copy constr ConiferousTree"<<endl;
        /*name=other.name;
        age=other.age;
        height=other.height;*/
        LeafShape=other.LeafShape;
    }
    ~ConiferousTree()
    {
        cout<<"Destr ConiferousTree"<<endl;
    }
    string Get_LeafShape()
    {
        return LeafShape;
    }
    void grow(int years)
    {
        Tree::grow(years);
        height +=years * 0.1;
        cout<<name<<"Coniferous tree growth"<<height<<endl;
    }
    void Show()
    {
        Tree::Show();
        cout<<"LeafShape - "<<LeafShape<<endl;
    }
};
template <typename LeafType>
class DeciduousTree:public Tree//лиственное дерево
{
protected:
    LeafType Leaf;
    int AnnualRings;
public:
    DeciduousTree(string Name,int a,double h,LeafType type,int rings):Tree(Name,a,h)
    {
        cout<<"Initializing constr DeciduousTree"<<endl;
        /*name=Name;
        age=a;
        height=h;*/
        Leaf=type;
        if (rings <=0)
        {
            cout<<"Rings cannot <=0"<<endl;
            AnnualRings=0;
        }
        else
        {
            AnnualRings=rings;
        }
    }
    ~DeciduousTree()
    {
        cout<<"Destr DeciduousTree"<<endl;
    }
    void grow(int years)
    {
        Tree::grow(years);
        height += years *1.5;
        AnnualRings += years;
        cout<<name<<" - Deciduous tree growth - "<<height<<"\nRings = "<<AnnualRings<<endl;
    }
    void Show()
    {
        Tree::Show();
        cout<<"Leaf Type - "<<Leaf<<endl;
        cout<<"Rings - "<<AnnualRings<<endl;
    }
    LeafType Get_Leaf()
    {
        return Leaf;
    }
    int Get_AnnualRings()
    {
        return AnnualRings;
    }
};
int main()
{
    //статичесмкий метод диаметра
    /*double Height=10.0;
    cout<<"Diametr tree"<<Height<<Tree::DiameterTree(Height)<<endl;
    cout<<"-------"<<endl;*/
    //Пример раннего связывания
    Tree Early("Erly",31,56.8);
    cout<<"-------"<<endl;
    Early.Show();
    cout<<"-------"<<endl;
    //инициализирующий для дерева
    Tree *oak=new Tree("Dub",-50,-15.5);
    cout<<"-------"<<endl;
    oak->Show();
    oak->grow(-2);
    oak->DiameterTree(5);
    cout<<"-------"<<endl;
    //инициализирующий для хвойного дерева
    Tree *pine=new ConiferousTree("El",30,20.1,"square");//инициализирующий для хвоного дерева
    pine->Show();
    pine->AbsorbWater();
    cout<<"-------"<<endl;
    //Конструктор по умолчанию
    Tree *he=new Tree();
    he->Show();
    he->DiameterTree(2);
    cout<<"-------"<<endl;
    //копирующий конструктор
    Tree *copyedoak=new Tree(*oak);
    copyedoak->Show();
    copyedoak->grow(2);
    cout<<"-------"<<endl;
    //пример оператора <<
    Tree* ko=new Tree("REW",12,12.7);
    cout<<"Operator <<\n"<<ko<<endl;
    cout<<"-------"<<endl;
    //пример оператора >>
    /*Tree* ok=new Tree();// для оператора >>
    cout<<"Operator >>\n"<<endl;
    cin>>ok;
    ok->Show();*/
    cout<<"-------"<<endl;
    //использование шаблона
    DeciduousTree<double> *lTree=new DeciduousTree<double>("IOIOII",54,78.56,76.4,-100);
    lTree->Show();
    lTree->grow(5);
    lTree->photosynthesis();
    cout<<"-------"<<endl;
    cout<<"Count Obj = "<<Tree::Get_ObjCount()<<endl;//счетчик
    cout<<"-------"<<endl;
    //delete ok;
    delete oak;
    cout<<"-------"<<endl;
    delete pine;
    cout<<"-------"<<endl;
    delete he;
    cout<<"-------"<<endl;
    delete ko;
    cout<<"-------"<<endl;
    delete lTree;
    cout<<"-------"<<endl;
    return 0;
}
