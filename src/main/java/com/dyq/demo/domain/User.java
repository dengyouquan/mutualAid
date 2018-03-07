package com.dyq.demo.domain;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = {"addresses","blogs","documents", "rewards","comments","documentComments","blogComments",
        "documentReplys","blogReplys","toDocumentReplys","toBlogReplys","votes","downVotes","dcvotes","dcdownVotes"})
@Entity
public class User extends BaseData implements UserDetails {

    @NotEmpty(message = "姓名不能为空")
    @Size(min=2, max=20)
    @Column(nullable = false, length = 20) // 映射为字段，值不能为空
    private String name;

    @NotEmpty(message = "邮箱不能为空")
    @Size(max=50)
    @Email(message= "邮箱格式不对" )
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotEmpty(message = "账号不能为空")
    @Size(min=3, max=20)
    @Column(nullable = false, length = 20, unique = true)
    private String username; // 用户账号，用户登录时的唯一标识

    @NotEmpty(message = "密码不能为空")
    @Size(max=100)
    @Column(length = 100)
    private String password; // 登录时密码

    @Column(length = 200)
    private String avatar; // 头像图片地址

    //性别 0: 女 1: 男 2:其他
    private int sex;
    private int integral;//积分
    private int type;//用户擅长领域，教师用
    private int level;//星级，教师用
    private int praiseSize;//好评量，教师用
    private int questionSize;//解决问题数量，教师用

    //电话
    @Pattern(regexp = "^1[34578]\\d{9}$")//正则注解
    private String tel;

    @Temporal(TemporalType.DATE)//(精确到年月日)
    private Date birth;

    private String classify;//职业

    @Column
    private int failtime; // 登录失败次数

    private boolean accountNonLocked = true;
    private boolean enable = true;

    //地址信息
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();

    //博客信息
    @OneToMany(mappedBy="blogUser",cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)//博客，即使用户不在，保留
    private Set<Blog> blogs = new HashSet<>();


    //上传文档信息
    @OneToMany(mappedBy="documentUser",cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Set<Document> documents = new HashSet<>();

    //悬赏文档信息
    @OneToMany(mappedBy="rewardUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Reward> rewards = new HashSet<>();

    //资料评论信息
    @OneToMany(mappedBy="documentCommentUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentComment> documentComments = new HashSet<>();

    //博客评论信息
    @OneToMany(mappedBy="blogCommentUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlogComment> blogComments = new HashSet<>();

    //资料回复信息
    @OneToMany(mappedBy="documentReplyUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentReply> documentReplys = new HashSet<>();

    //博客回复信息
    @OneToMany(mappedBy="blogReplyUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlogReply> blogReplys = new HashSet<>();

    //资料回复目标信息
    @OneToMany(mappedBy="toDocumentReplyUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentReply> toDocumentReplys = new HashSet<>();

    //博客回复目标信息
    @OneToMany(mappedBy="toBlogReplyUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlogReply> toBlogReplys = new HashSet<>();

    //角色信息
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "join_user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    //标签信息
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "join_user_tag", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<UserTag> userTags = new HashSet<>();

    //点赞信息
    /*@OneToOne(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Vote vote;*/
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Vote> votes = new HashSet<>();

    //点踩信息
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DownVote> downVotes = new HashSet<>();

    //评论点赞信息
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DCVote> dcvotes = new HashSet<>();

    //评论点踩信息
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DCDownVote> dcdownVotes = new HashSet<>();

    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        this.password = encodePasswd;
    }

    public User(String name, String email,String username,String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.failtime = 0;
        accountNonLocked = true;
        enable = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for(GrantedAuthority authority : this.authorities){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
