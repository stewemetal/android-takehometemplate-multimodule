# Claude Code Quick Start

Get up and running with Claude Code automation in 5 minutes!

## ⚡ Quick Setup

### Step 1: Add API Key (2 minutes)

1. Get your Anthropic API key from https://console.anthropic.com/
2. In GitHub: **Settings** → **Secrets and variables** → **Actions** → **New repository secret**
3. Name: `ANTHROPIC_API_KEY`
4. Value: Your API key
5. Click **Add secret**

### Step 2: Create Labels (1 minute)

Run the setup script:

```bash
./.github/scripts/setup-labels.sh
```

Or manually create these labels in GitHub:
- `claude-code` (color: #7B68EE)
- `claude-in-progress` (color: #FFA500)
- `automated` (color: #0E8A16)
- `needs-review` (color: #FBCA04)

### Step 3: Test It! (2 minutes)

1. **Create a test issue** → Use the "Claude Code Task" template
2. **Fill in** a simple request:
   ```
   ## Description
   Add a hello world function to MainActivity

   ## Acceptance Criteria
   - [ ] Function prints "Hello, Claude Code!"
   - [ ] Called in onCreate
   ```
3. **Label it** with `claude-code`
4. **Watch** the magic happen! ✨

## 🎉 That's It!

Claude Code will:
- ✅ Read your issue
- ✅ Implement the solution
- ✅ Run tests
- ✅ Create a PR for you to review

## 📖 Learn More

- [Full Setup Guide](CLAUDE_CODE_SETUP.md)
- [View PR #139](https://github.com/stewemetal/android-takehometemplate-multimodule/pull/139)

## 💡 Pro Tips

### Write Better Issues

❌ Bad: "add dark mode"
✅ Good: Clear requirements + acceptance criteria + constraints

### Control Costs

- Start with small issues
- Monitor usage at https://console.anthropic.com/settings/usage
- Default limit: 10 tasks/day (configurable)

### Best Use Cases

✅ **Good for:**
- New feature screens
- Bug fixes with clear repro
- Refactoring tasks
- Adding tests

❌ **Not ideal for:**
- Vague requirements
- Security-critical changes
- One-liner fixes (faster to do manually)

## 🆘 Troubleshooting

**Issue**: Workflow doesn't start
- ✓ Check the `claude-code` label is applied
- ✓ Verify ANTHROPIC_API_KEY secret exists
- ✓ Check Actions tab for errors

**Issue**: "Rate limit exceeded"
- ✓ Max 10 tasks/day by default
- ✓ Edit workflow file to increase if needed

**Issue**: "Manual setup required" comment
- ✓ Claude Code CLI needs configuration
- ✓ See full setup guide for details

## 🎯 Next Steps

Once you're comfortable:
1. Adjust rate limits in the workflow
2. Customize the issue template
3. Add more example issues
4. Share with your team!

Happy coding with AI! 🚀
