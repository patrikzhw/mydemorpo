mydemorpo
=========

demo git report test
same thing, also I've tried to update eclipse to helios sr1 - still no luck, please help. What i've tried - from GIT command line (msysgit) ssh git@myname.beanstalk.com - everything fine - it asked a password and it's ok. In eclipse I tried: user: git, protocol: ssh, everything else as in clone. Then eclipse asked me for password for user git, I've entered and nothing. By the way, maybe someone knows where eclipse ssh stores it's logs, it might be helpful. 每 gasan Sep 30 '10 at 22:51
Figured it out: kevinquillen.com/?p=457 每 Kevin Oct 1 '10 at 12:25
I've tried to put keys into ssh directory. From the other point, it's possible to configure where keys will be stored (Prefs -> Network -> SSH2), by default it searches in ssh directory, but it's possible to set any other directory. The problem arise is when I'm trying to clone and EGit asks for password for user git for key in ssh directory. When I put my git password, it says auth fail. The worst thing - I can't even find the log of the EGit or the connection to find out where is the problem. P.S. command line connection works perfectly. 每 gasan Oct 1 '10 at 13:19
Yes, same problem I had. Try deleting your keys and restarting, authenticate in the console before opening Eclipse. Copy files from .ssh to ssh, then attempt a clone. When I did that, I was finally able to do it in Eclipse. Eclipse should then ask for your keypass, leave git protocol and authentication fields blank. 每 Kevin Oct 1 '10 at 13:31
are you sure about git protocol? I thought that protocol should be ssh and in authentication fields username must be 'git'. 每 gasan Oct 1 '10 at 13:36
could you please give a screenshot for your beanstalk EGit repository connection screen? 每 gasan Oct 1 '10 at 13:40
I never touched the protocol or auth fields. When you click COPY on the repo link in beanstalk then click Clone in Eclipse, the only thing you then have to do is click Next. 每 Kevin Oct 1 '10 at 14:03
@Thank you very much. Regenerating keys really helped. And I think I know what the problem was. It's only an assumption, but at first I've copied my public key to my beanstalk profile without ending linebreak, it seems that for command line git it's not critical, but it is that for eclipse. In the second try, I've copied all public key to my profile, including line break and now everything works in eclipse too. 每 gasan Oct 2 '10 at 13:17
feedback
2 Answersactiveoldestvotes
up vote
0
down vote
I've made a discussion on the Beanstalk help center here. Could you please contribute there?

link|improve this answer
answered Oct 1 '10 at 9:44

gasan
1,576316
I was able to get it, forgot to post update: kevinquillen.com/?p=457 每 Kevin Oct 1 '10 at 12:25
feedback

up vote
0
down vote
Although this comment may not be not be directly related to the case of cloning from Beanstalk specifically, this EGit bug in general affects cloning from any repository and not just Beanstalk, it has been reported here along with a working workaround: https://bugs.eclipse.org/bugs/show_bug.cgi?id=326526

Posting this comment just in case anyone stumble here looking for a solution for this EGit bug.

link|improve this answer
answered Oct 13 '11 at 13:56

Hesham